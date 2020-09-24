package br.com.accenture.challenger.configs.exceptionhandler.gateway.http.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import br.com.accenture.challenger.configs.exceptionhandler.base.ChallengerBaseException;
import br.com.accenture.challenger.configs.exceptionhandler.gateway.http.controller.json.ExceptionJson;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerExceptionHandler {
	
	private static final String SAO_CONSTRAINT_VIOLATION = "challenger.constraintViolation";
	private static final String SAO_EXCEPTION_HANDLER_ERROR_TO_RESOLVE_EXCEPTION = "challenger.exceptionHandler.errorToResolveException";
	private static final String ERROR_TO_RESOLVE_EXCEPTION_HANDLER = "Error to resolve exception handler";
	private static final String SAO_ARGUMENT_NOT_VALID = "challenger.argumentNotValid";
	private static final String MISSING_PARAM = "missingParam";
	private static final String BODY_INCORRECT_VALUE = "body.incorrectValue";


	@ExceptionHandler({ ChallengerBaseException.class})
	@ResponseBody
	public ResponseEntity<ExceptionJson> saoException(final ChallengerBaseException e, final HttpServletResponse response) {
		log.error(e.getMessage(), e);
		final ExceptionJson exceptionJson = new ExceptionJson(e);
		return new ResponseEntity<>(exceptionJson, new HttpHeaders(), e.getHttpStatus());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Throwable.class)
	@ResponseBody
	public ExceptionJson genericError(final Throwable e) {
		log.error(e.getMessage(), e);
		return new ExceptionJson(HttpStatus.INTERNAL_SERVER_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
	}
	
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ExceptionJson handleHttpMessageNotReadableException(final HttpMessageNotReadableException ex) {
		log.error(ex.getMessage(), ex);

		if ( !StringUtils.isEmpty(ex.getMessage()) && ";".contains(ex.getMessage())) {

			final Matcher matcher = Pattern.compile("(JSON parse error: Cannot deserialize value of type (.*)) from ((.*))") //
					.matcher(StringUtils.split(ex.getMessage(), ";")[0]);

			if (matcher.matches()) {
				return new ExceptionJson(BODY_INCORRECT_VALUE, matcher.group(3));
			}
		}

		return new ExceptionJson(BODY_INCORRECT_VALUE, ex.getLocalizedMessage());
	}

	@ExceptionHandler(ServletRequestBindingException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ExceptionJson handleRequiredParameters(final ServletRequestBindingException ex) {
		log.error(ex.getMessage(), ex);
		return new ExceptionJson(MISSING_PARAM, ex.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseBody
	public ExceptionJson handlerMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException e) {
		log.error(e.getMessage(), e);
		return new ExceptionJson(e.getName(), "Failed to convert " + e.getValue());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ExceptionJson validationException(final MethodArgumentNotValidException methodArgumentNotValidException) {
		log.error(methodArgumentNotValidException.getMessage(), methodArgumentNotValidException);
		try {
			final StringBuffer errors = new StringBuffer();
			
			methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(field -> {
				errors.append(field.getField() + ":" + field.getDefaultMessage()+";");
			});
			
			return new ExceptionJson(SAO_ARGUMENT_NOT_VALID, errors.toString());
			
		} catch (Exception exception) {
			log.error(ERROR_TO_RESOLVE_EXCEPTION_HANDLER, exception);
			return new ExceptionJson(SAO_EXCEPTION_HANDLER_ERROR_TO_RESOLVE_EXCEPTION, exception.getMessage());
		}
	}


	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public ExceptionJson violationException(final ConstraintViolationException e) {
		log.error(e.getMessage(), e);
		final StringBuffer errors = new StringBuffer();

		e.getConstraintViolations().forEach(
				constraintViolation -> errors.append(constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage()+";"));

		return new ExceptionJson(SAO_CONSTRAINT_VIOLATION, errors.toString());
	}
}
