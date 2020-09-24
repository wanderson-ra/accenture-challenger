package br.com.accenture.challenger.gateways.http.controllers.task;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.accenture.challenger.domains.Task;
import br.com.accenture.challenger.gateways.http.controllers.task.json.TaskJson;
import br.com.accenture.challenger.usecases.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("${baseurl.v1}/tasks")
@Api(tags = "Task", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class TaskController {

	@Autowired
	private TaskService taskService;

	@ApiOperation(value = "Resource to get companies", response = TaskJson.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 422, message = "Unprocessable Entity"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@Validated
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public List<TaskJson> getAllTasks() {
		log.trace("Get All Taks, TaskController");

		final List<Task> tasks = this.taskService.getAllTasks();
		final List<TaskJson> tasksJsonResponse = tasks.stream().map(task -> this.mapperTaskJsonFromTask(task))
				.collect(Collectors.toList());
		
		return tasksJsonResponse;
	}

	private TaskJson mapperTaskJsonFromTask(final Task task) {

		return new TaskJson(task.getId(), task.getDescription(), task.getDate(), task.getIsDone());
	}
}
