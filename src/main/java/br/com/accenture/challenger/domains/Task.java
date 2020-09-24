package br.com.accenture.challenger.domains;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@Entity
public class Task {

	@Id
	@GeneratedValue
	private Long id;
	private String description;
	private LocalDate date;
	
	@Column(columnDefinition = "boolean default false")
	private Boolean isDone;

	public Task(final String description, final LocalDate date) {
		this.description = description;
		this.date = date;

	}
}
