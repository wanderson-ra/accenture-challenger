package br.com.accenture.challenger.usecases.exceptions;

import org.springframework.http.HttpStatus;

import br.com.accenture.challenger.configs.exceptionhandler.base.ChallengerBaseException;
import lombok.Getter;

@Getter
public class TaskNotFountBusinessException extends ChallengerBaseException {

	private static final long serialVersionUID = 9119663329415899802L;
	
	public final String code = "challenger.error.taskNotFound";
	public final String message = "Task Not Found.";
	public final HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY; 
}
