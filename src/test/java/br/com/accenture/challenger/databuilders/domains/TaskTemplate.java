package br.com.accenture.challenger.databuilders.domains;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

import br.com.accenture.challenger.domains.Task;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class TaskTemplate {
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy");

	public static final String GET_ALL_TASKS = "GET_ALL_TASKS";
	public static final String CREATE_TASK = "CREATE_TASK";
	public static final String CREATED = "CREATED";

	protected static void load() {
		Fixture.of(Task.class).addTemplate(GET_ALL_TASKS, new Rule() {
			{

				add("id", random(Long.class, range(1L, 100L)));
				add("description", random("description_01", "description_02"));
				add("date", random(LocalDate.class, LocalDate.parse("10/11/2019", FORMATTER),
						LocalDate.parse("10/12/2019", FORMATTER)));
				add("isDone", random(Boolean.class, true, false));
			}
		});

		Fixture.of(Task.class).addTemplate(CREATE_TASK, new Rule() {
			{

				add("description", random("description_01", "description_02"));
				add("date", random(LocalDate.class, LocalDate.parse("10/11/2019", FORMATTER),
						LocalDate.parse("10/12/2019", FORMATTER)));

			}
		});
		
		Fixture.of(Task.class).addTemplate(CREATED, new Rule() {
			{

				add("id", random(Long.class, range(1L, 100L)));
				add("description", random("description_01", "description_02"));
				add("date", random(LocalDate.class, LocalDate.parse("10/11/2019", FORMATTER),
						LocalDate.parse("10/12/2019", FORMATTER)));
				add("isDone", false);
			}
		});
	}

}