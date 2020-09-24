package br.com.accenture.challenger.gateways.http.controllers.task.json;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class CreateTaskRequestJson {
	@NotBlank
	@ApiModelProperty(required = true)
	@Valid
	private String description;

	@NotNull
	@ApiModelProperty(required = true)
	@Valid
	private LocalDate date;
}
