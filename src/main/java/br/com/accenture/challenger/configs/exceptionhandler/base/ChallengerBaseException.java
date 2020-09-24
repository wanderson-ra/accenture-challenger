package br.com.accenture.challenger.configs.exceptionhandler.base;

import org.springframework.http.HttpStatus;

public abstract class ChallengerBaseException extends RuntimeException {
	private static final long serialVersionUID = 2115411860896771334L;

	public abstract String getCode();
	public abstract HttpStatus getHttpStatus();
	public abstract String getMessage();
}
