package br.com.accenture.challenger.usecases;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

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

import br.com.accenture.challenger.databuilders.domains.DomainsTemplateLoader;
import br.com.accenture.challenger.databuilders.domains.TaskTemplate;
import br.com.accenture.challenger.domains.Task;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class TaskServiceUniTest {

	@InjectMocks
	private TaskService taskService;

	@Mock
	private GetAllTasksUseCase getAllTasksUseCase;

	@Mock
	private CreateTaskUseCase createTaskUseCase;

	@Mock
	private DeleteTaskUseCase deleteTaskUseCase;
	
	@Mock
	private GetTaskByIdUseCase getTaskByIdUseCase;
	
	@Mock
	private MarkTaskIsDoneUseCase markTaskIsDoneUseCase;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeClass
	public static void init() {
		FixtureFactoryLoader.loadTemplates(ClassUtils.getPackageName(DomainsTemplateLoader.class));
	}

	@Test
	public void getAllTasksWithSuccess() {
		final List<Task> tasks = Fixture.from(Task.class).gimme(2, TaskTemplate.GET_ALL_TASKS);
		when(this.taskService.getAll()).thenReturn(tasks);

		final List<Task> tasksResponse = this.taskService.getAll();

		assertEquals(tasks.get(0).getDate(), tasksResponse.get(0).getDate());
		assertEquals(tasks.get(0).getDescription(), tasksResponse.get(0).getDescription());
		assertEquals(tasks.get(0).getId(), tasksResponse.get(0).getId());
		assertEquals(tasks.get(0).getIsDone(), tasksResponse.get(0).getIsDone());

		assertEquals(tasks.get(1).getDate(), tasksResponse.get(1).getDate());
		assertEquals(tasks.get(1).getDescription(), tasksResponse.get(1).getDescription());
		assertEquals(tasks.get(1).getId(), tasksResponse.get(1).getId());
		assertEquals(tasks.get(1).getIsDone(), tasksResponse.get(1).getIsDone());
	}

	@Test
	public void createWithSuccess() {
		final Task task = Fixture.from(Task.class).gimme(TaskTemplate.CREATE_TASK);
		final Task taskCreated = Fixture.from(Task.class).gimme(TaskTemplate.CREATED);		

		when(this.createTaskUseCase.create(task)).thenReturn(taskCreated);

		final Task taskResponse = this.taskService.create(task);

		final ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);

		verify(this.createTaskUseCase, VerificationModeFactory.times(1)).create(taskCaptor.capture());

		final Task taskCaptured = taskCaptor.getValue();

		assertEquals(taskCreated.getId(), taskResponse.getId());
		assertEquals(taskCreated.getDate(), taskResponse.getDate());
		assertEquals(taskCreated.getDescription(), taskResponse.getDescription());
		assertEquals(taskCreated.getIsDone(), taskResponse.getIsDone());		
		
		assertEquals(task.getDate(), taskCaptured.getDate());
		assertEquals(task.getDescription(), taskCaptured.getDescription());		
	}

	@Test
	public void deleteWithSuccess() {
		final Task task = Fixture.from(Task.class).gimme(TaskTemplate.GET_TASK);
		final Long taskId = 300L;
		
		when(this.getTaskByIdUseCase.get(taskId)).thenReturn(task);

		this.taskService.delete(taskId);
		
		final ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
		verify(this.deleteTaskUseCase, VerificationModeFactory.times(1)).delete(taskCaptor.capture());	

		final Task taskCaptured = taskCaptor.getValue();
		assertEquals(task.getDate(), taskCaptured.getDate());
		assertEquals(task.getDescription(), taskCaptured.getDescription());
		assertEquals(task.getId(), taskCaptured.getId());
		assertEquals(task.getIsDone(), taskCaptured.getIsDone());
	}
	
	@Test
	public void markIsDoneWithSuccess() {
		final Task task = Fixture.from(Task.class).gimme(TaskTemplate.GET_TASK);
		final Long taskId = 300L;
		
		when(this.getTaskByIdUseCase.get(taskId)).thenReturn(task);

		this.taskService.markIsDone(taskId);
		
		final ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
		verify(this.markTaskIsDoneUseCase, VerificationModeFactory.times(1)).mark(taskCaptor.capture());	

		final Task taskCaptured = taskCaptor.getValue();
		assertEquals(task.getDate(), taskCaptured.getDate());
		assertEquals(task.getDescription(), taskCaptured.getDescription());
		assertEquals(task.getId(), taskCaptured.getId());
		assertEquals(task.getIsDone(), taskCaptured.getIsDone());
	}
}
