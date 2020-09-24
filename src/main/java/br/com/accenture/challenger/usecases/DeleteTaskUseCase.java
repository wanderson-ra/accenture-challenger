package br.com.accenture.challenger.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.accenture.challenger.gateways.database.task.TaskDatabaseGateway;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DeleteTaskUseCase {
	
	@Autowired
	private TaskDatabaseGateway taskDatabaseGateway;

	protected void delete(final Long taskId) {
		log.trace("taskId: {}", taskId);
		
		this.taskDatabaseGateway.delete(taskId);
		
		log.trace("taskId: {}", taskId);
	}
}
