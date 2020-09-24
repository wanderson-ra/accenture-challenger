package br.com.accenture.challenger.gateways.http.controllers.task.json;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class TaskJson {	
	private Long id;
	private String description;
	private LocalDate date;
	private Boolean isDone;
}
