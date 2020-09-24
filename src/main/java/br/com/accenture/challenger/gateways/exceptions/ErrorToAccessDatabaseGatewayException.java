package br.com.accenture.challenger.gateways.exceptions;

import org.springframework.http.HttpStatus;
import br.com.accenture.challenger.configs.exceptionhandler.base.ChallengerBaseException;
import lombok.Getter;

@Getter
public class ErrorToAccessDatabaseGatewayException extends ChallengerBaseException {
	
	private static final long serialVersionUID = -6037701934150521231L;
	
	public final String code = "challenger.error.database.access";
	public final String message = "Error to access database.";
	public final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR; 

}

