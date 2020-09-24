package br.com.accenture.challenger.gateways.database.task;

import java.util.List;

import java.util.Optional;

import br.com.accenture.challenger.domains.Task;

public interface TaskDatabaseGateway {
	List<Task> getAll();
	Long create(final Task task);
	void delete(final Task task);
	Optional<Task> getById(final Long taskId);
}
