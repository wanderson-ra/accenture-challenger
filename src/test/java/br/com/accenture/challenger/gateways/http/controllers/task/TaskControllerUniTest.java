package br.com.accenture.challenger.gateways.http.controllers.task;

import java.util.List;

import org.apache.commons.lang3.ClassUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

import br.com.accenture.challenger.databuilders.cases.gateways.http.controllers.task.CreateTaskRequestJsonTemplate;
import br.com.accenture.challenger.databuilders.cases.gateways.http.controllers.task.TaskControllerTemplateLoader;
import br.com.accenture.challenger.databuilders.domains.DomainsTemplateLoader;
import br.com.accenture.challenger.databuilders.domains.TaskTemplate;
import br.com.accenture.challenger.domains.Task;
import br.com.accenture.challenger.gateways.http.controllers.task.json.CreateTaskRequestJson;
import br.com.accenture.challenger.gateways.http.controllers.task.json.CreateTaskResponseJson;
import br.com.accenture.challenger.gateways.http.controllers.task.json.TaskJson;
import br.com.accenture.challenger.usecases.TaskService;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class TaskControllerUniTest {

	@InjectMocks
	private TaskController taskController;

	@Mock
	private TaskService taskService;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeClass
	public static void init() {
		FixtureFactoryLoader.loadTemplates(ClassUtils.getPackageName(DomainsTemplateLoader.class));
		FixtureFactoryLoader.loadTemplates(ClassUtils.getPackageName(TaskControllerTemplateLoader.class));
	}

	@Test
	public void getAllWithSuccess() {

		final List<Task> tasks = Fixture.from(Task.class).gimme(2, TaskTemplate.GET_ALL_TASKS);
		when(this.taskService.getAll()).thenReturn(tasks);

		final List<TaskJson> taskJsons = this.taskController.getAll();

		assertEquals(tasks.get(0).getDate(), taskJsons.get(0).getDate());
		assertEquals(tasks.get(0).getDescription(), taskJsons.get(0).getDescription());
		assertEquals(tasks.get(0).getId(), taskJsons.get(0).getId());
		assertEquals(tasks.get(0).getIsDone(), taskJsons.get(0).getIsDone());

		assertEquals(tasks.get(1).getDate(), taskJsons.get(1).getDate());
		assertEquals(tasks.get(1).getDescription(), taskJsons.get(1).getDescription());
		assertEquals(tasks.get(1).getId(), taskJsons.get(1).getId());
		assertEquals(tasks.get(1).getIsDone(), taskJsons.get(1).getIsDone());
	}

	@Test
	public void createWithSuccess() {
		final CreateTaskRequestJson createTaskRequestJson = Fixture.from(CreateTaskRequestJson.class)
				.gimme(CreateTaskRequestJsonTemplate.CREATE_TASK_REQUEST);
		
		final Task task = Fixture.from(Task.class).gimme(TaskTemplate.CREATED);		
			
		when(this.taskService.create(any(Task.class))).thenReturn(task);
		
		final CreateTaskResponseJson createTaskResponseJson = this.taskController.create(createTaskRequestJson);
		
		final ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
		
		verify(this.taskService, VerificationModeFactory.times(1)).create(taskCaptor.capture());
		
		final Task taskCaptured = taskCaptor.getValue();
		
		assertEquals(task.getId(), createTaskResponseJson.getId());
		assertEquals(task.getDate(), createTaskResponseJson.getDate());
		assertEquals(task.getDescription(), createTaskResponseJson.getDescription());
		assertEquals(task.getIsDone(), createTaskResponseJson.getIsDone());		
		
		assertEquals(createTaskRequestJson.getDate(), taskCaptured.getDate());
		assertEquals(createTaskRequestJson.getDescription(), taskCaptured.getDescription());
	}
	
	@Test
	public void deleteWithSuccess() {
		
		final Long taskId = 300L;
		
		this.taskController.delete(taskId);
		
		verify(this.taskService, VerificationModeFactory.times(1)).delete(taskId);
	}
	
	@Test
	public void markIsDoneWithSuccess() {
		
		final Long taskId = 300L;
		
		this.taskController.markIsDone(taskId);
		
		verify(this.taskService, VerificationModeFactory.times(1)).markIsDone(taskId);
	}
}
