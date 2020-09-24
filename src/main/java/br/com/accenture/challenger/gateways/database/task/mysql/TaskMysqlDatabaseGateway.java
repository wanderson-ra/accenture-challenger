package br.com.accenture.challenger.gateways.database.task.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import br.com.accenture.challenger.domains.Task;
import br.com.accenture.challenger.gateways.database.task.TaskDatabaseGateway;
import br.com.accenture.challenger.gateways.database.task.mysql.repository.TaskRepository;
import br.com.accenture.challenger.gateways.exceptions.ErrorToAccessDatabaseGatewayException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TaskMysqlDatabaseGateway implements TaskDatabaseGateway {

	@Autowired
	private TaskRepository taskRepository;

	public List<Task> getAll() {

		try {
			log.trace("Get All Taks, TaskMysqlDatabaseGateway");

			final List<Task> tasks = this.taskRepository.findAll();

			log.trace("tasks: {}", tasks);

			return tasks;

		} catch (Exception error) {
			log.error("Error, {}", error);
			throw new ErrorToAccessDatabaseGatewayException();
		}
	}

	public Long save(final Task task) {

		try {
			log.trace("task: {}", task);

			final Task taskCreated = this.taskRepository.save(task);

			log.trace("taskId: {}", taskCreated.getId());

			return taskCreated.getId();

		} catch (Exception error) {
			log.error("Error, {}", error);
			throw new ErrorToAccessDatabaseGatewayException();
		}
	}

	public void delete(final Task task) {
		try {
			log.trace("taskId: {}", task);

			this.taskRepository.delete(task);

		} catch (Exception error) {
			log.error("Error, {}", error);
			throw new ErrorToAccessDatabaseGatewayException();
		}

	}

	public Optional<Task> getById(Long taskId) {

		try {
			log.trace("taskId: {}", taskId);

			final Optional<Task> optionalTask = this.taskRepository.findById(taskId);

			return optionalTask;
		} catch (Exception error) {
			log.error("Error, {}", error);
			throw new ErrorToAccessDatabaseGatewayException();
		}

	}

}
