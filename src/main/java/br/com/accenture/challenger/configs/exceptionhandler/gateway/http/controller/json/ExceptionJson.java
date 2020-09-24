package br.com.accenture.challenger.configs.exceptionhandler.gateway.http.controller.json;


import br.com.accenture.challenger.configs.exceptionhandler.base.ChallengerBaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class ExceptionJson {

	private final String code;
	private final String message;
	
	public ExceptionJson(final ChallengerBaseException baseException) {
		this.code = baseException.getCode();
		this.message = baseException.getMessage();
	}
}
