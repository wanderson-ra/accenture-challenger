package br.com.accenture.challenger.databuilders.cases.gateways.http.controllers.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.accenture.challenger.gateways.http.controllers.task.json.CreateTaskRequestJson;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class CreateTaskRequestJsonTemplate {
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy");

	public static final String CREATE_TASK_REQUEST = "CREATE_TASK_REQUEST";

	protected static void load() {
		Fixture.of(CreateTaskRequestJson.class).addTemplate(CREATE_TASK_REQUEST, new Rule() {
			{

				add("description", random("description_01", "description_02"));
				add("date", random(LocalDate.class, LocalDate.parse("10/11/2019", FORMATTER),
						LocalDate.parse("10/12/2019", FORMATTER)));
			}
		});
	}
}