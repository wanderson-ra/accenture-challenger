package br.com.accenture.challenger.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.accenture.challenger.domains.Task;
import br.com.accenture.challenger.gateways.database.task.TaskDatabaseGateway;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DeleteTaskUseCase {
	
	@Autowired
	private TaskDatabaseGateway taskDatabaseGateway;	

	protected void delete(final Task task) {
		log.trace("taskId: {}", task);
		
		this.taskDatabaseGateway.delete(task);
		
	}
}
