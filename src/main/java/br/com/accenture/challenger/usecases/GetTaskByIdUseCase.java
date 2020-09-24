package br.com.accenture.challenger.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import br.com.accenture.challenger.domains.Task;
import br.com.accenture.challenger.gateways.database.task.TaskDatabaseGateway;
import br.com.accenture.challenger.usecases.exceptions.TaskNotFountBusinessException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GetTaskByIdUseCase {

	@Autowired
	private TaskDatabaseGateway taskDatabaseGateway;
	
	protected Task get (final Long taskId) {
		
		log.trace("taskId: {}", taskId);
		
		final Optional<Task> optionalTask = this.taskDatabaseGateway.getById(taskId);
		
		if(!optionalTask.isPresent()) {
			throw new TaskNotFountBusinessException();
		}
		
		log.trace("task: {}", optionalTask.get());
		
		return optionalTask.get();
	}
}
