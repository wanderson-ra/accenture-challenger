package br.com.accenture.challenger.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.accenture.challenger.domains.Task;
import br.com.accenture.challenger.gateways.database.task.TaskDatabaseGateway;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MarkTaskIsDoneUseCase {

	@Autowired
	private TaskDatabaseGateway taskDatabaseGateway;

	public void mark(final Task task) {

		log.trace("task: {}", task);
		final Task taskToBeUpdated = new Task(task.getId(), task.getDescription(), task.getDate(), true);

		this.taskDatabaseGateway.markIsDone(taskToBeUpdated);
	}
}
