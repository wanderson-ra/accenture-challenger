package br.com.accenture.challenger.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.accenture.challenger.domains.Task;
import br.com.accenture.challenger.gateways.database.task.TaskDatabaseGateway;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GetAllTasksUseCase {
	
	@Autowired
	private TaskDatabaseGateway databaseGateway;
	
	protected List<Task> get (){		
		log.trace("Get All Taks, GetAllTasksUseCase");
		
		final List<Task> tasks = this.databaseGateway.getAllTasks();
		
		log.trace("taks: {}", tasks);
		
		return tasks;
	}
}
