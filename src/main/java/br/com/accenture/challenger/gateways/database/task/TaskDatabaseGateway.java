package br.com.accenture.challenger.gateways.database.task;

import java.util.List;

import br.com.accenture.challenger.domains.Task;

public interface TaskDatabaseGateway {
	List<Task> getAllTasks();
	Long createTask(final Task task);
}
