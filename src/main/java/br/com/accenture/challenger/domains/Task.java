package br.com.accenture.challenger.domains;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Task {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private LocalDate date;
	
	@Column(columnDefinition = "boolean default false", nullable = false)
	private Boolean isDone = false;

	public Task(final String description, final LocalDate date) {
		this.description = description;
		this.date = date;

	}
}
