package br.com.accenture.challenger.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.accenture.challenger.domains.Task;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TaskService {

	@Autowired
	private GetAllTasksUseCase getAllTasksUseCase;
	
	@Autowired
	private CreateTaskUseCase createTaskUseCase;

	public List<Task> getAll() {
		log.trace("Get All Taks, TaskService");

		final List<Task> tasks = this.getAllTasksUseCase.get();

		log.trace("tasks: {}", tasks);

		return tasks;
	}

	public Long create(final Task task) {
		
		log.trace("task: {}", task);
		
		final Long taskId = this.createTaskUseCase.create(task);

		log.trace("taskId: {}", taskId);
		
		return taskId;
	}
	
	public void delete(final Long taskId) {
		
	}

}
