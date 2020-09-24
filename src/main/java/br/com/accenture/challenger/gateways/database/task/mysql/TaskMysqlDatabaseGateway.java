package br.com.accenture.challenger.gateways.database.task.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

	public List<Task> getAllTasks() {
		
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
}
