package br.com.accenture.challenger.gateways.database.task;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

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
import br.com.accenture.challenger.gateways.database.task.mysql.TaskMysqlDatabaseGateway;
import br.com.accenture.challenger.gateways.database.task.mysql.repository.TaskRepository;
import br.com.accenture.challenger.gateways.exceptions.ErrorToAccessDatabaseGatewayException;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class TaskMysqlDatabaseGatewayUniTest {

	@InjectMocks
	private TaskMysqlDatabaseGateway taskMysqlDatabaseGateway;

	@Mock
	private TaskRepository taskRepository;

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
		when(this.taskRepository.findAll()).thenReturn(tasks);

		final List<Task> tasksResponse = this.taskMysqlDatabaseGateway.getAll();

		assertEquals(tasks.get(0).getDate(), tasksResponse.get(0).getDate());
		assertEquals(tasks.get(0).getDescription(), tasksResponse.get(0).getDescription());
		assertEquals(tasks.get(0).getId(), tasksResponse.get(0).getId());
		assertEquals(tasks.get(0).getIsDone(), tasksResponse.get(0).getIsDone());

		assertEquals(tasks.get(1).getDate(), tasksResponse.get(1).getDate());
		assertEquals(tasks.get(1).getDescription(), tasksResponse.get(1).getDescription());
		assertEquals(tasks.get(1).getId(), tasksResponse.get(1).getId());
		assertEquals(tasks.get(1).getIsDone(), tasksResponse.get(1).getIsDone());
	}

	@Test(expected = ErrorToAccessDatabaseGatewayException.class)
	public void getAllTasksWithErrorToAccessDatabase() {
		doThrow(new RuntimeException()).when(this.taskRepository).findAll();

		try {

			this.taskMysqlDatabaseGateway.getAll();

		} catch (ErrorToAccessDatabaseGatewayException e) {
			assertEquals("challenger.error.database.access", e.code);
			assertEquals("Error to access database.", e.message);
			throw e;
		}

	}

	@Test
	public void saveWithSuccess() {
		final Task taskCreate = Fixture.from(Task.class).gimme(TaskTemplate.CREATE_TASK);
		final Task taskCreated = Fixture.from(Task.class).gimme(TaskTemplate.CREATED);

		when(this.taskRepository.save(taskCreate)).thenReturn(taskCreated);

		final Task taskResponse = this.taskMysqlDatabaseGateway.save(taskCreate);

		final ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);

		verify(this.taskRepository, VerificationModeFactory.times(1)).save(taskCaptor.capture());

		final Task taskCaptured = taskCaptor.getValue();

		assertEquals(taskCreated.getId(), taskResponse.getId());
		assertEquals(taskCreated.getDate(), taskResponse.getDate());
		assertEquals(taskCreated.getDescription(), taskResponse.getDescription());
		assertEquals(taskCreated.getIsDone(), taskResponse.getIsDone());	
		
		assertEquals(taskCreate.getDate(), taskCaptured.getDate());
		assertEquals(taskCreate.getDescription(), taskCaptured.getDescription());
	}

	@Test(expected = ErrorToAccessDatabaseGatewayException.class)
	public void saveWithErrorToAccessDatabase() {
		final Task taskCreate = Fixture.from(Task.class).gimme(TaskTemplate.CREATE_TASK);
		doThrow(new RuntimeException()).when(this.taskRepository).save(taskCreate);

		try {

			this.taskMysqlDatabaseGateway.save(taskCreate);

		} catch (ErrorToAccessDatabaseGatewayException e) {
			assertEquals("challenger.error.database.access", e.code);
			assertEquals("Error to access database.", e.message);
			throw e;
		}

	}

	@Test
	public void deleteWithSucess() {
		final Task task = Fixture.from(Task.class).gimme(TaskTemplate.GET_TASK);

		this.taskMysqlDatabaseGateway.delete(task);

		final ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
		verify(this.taskRepository, VerificationModeFactory.times(1)).delete(taskCaptor.capture());

		final Task taskCaptured = taskCaptor.getValue();
		assertEquals(task.getDate(), taskCaptured.getDate());
		assertEquals(task.getDescription(), taskCaptured.getDescription());
		assertEquals(task.getId(), taskCaptured.getId());
		assertEquals(task.getIsDone(), taskCaptured.getIsDone());
	}

	@Test(expected = ErrorToAccessDatabaseGatewayException.class)
	public void deleteWithErrorToAccessDatabase() {
		final Task task = Fixture.from(Task.class).gimme(TaskTemplate.GET_TASK);
		doThrow(new RuntimeException()).when(this.taskRepository).delete(task);

		try {

			this.taskMysqlDatabaseGateway.delete(task);

		} catch (ErrorToAccessDatabaseGatewayException e) {
			assertEquals("challenger.error.database.access", e.code);
			assertEquals("Error to access database.", e.message);
			throw e;
		}

	}

	@Test
	public void getByIdWithSuccess() {
		final Task task = Fixture.from(Task.class).gimme(TaskTemplate.GET_TASK);
		final Long taskId = 400L;

		when(this.taskRepository.findById(taskId)).thenReturn(Optional.of(task));

		final Optional<Task> optionalTask = this.taskMysqlDatabaseGateway.getById(taskId);
		assertEquals(task.getDate(), optionalTask.get().getDate());
		assertEquals(task.getDescription(), optionalTask.get().getDescription());
		assertEquals(task.getId(), optionalTask.get().getId());
		assertEquals(task.getIsDone(), optionalTask.get().getIsDone());
	}

	@Test(expected = ErrorToAccessDatabaseGatewayException.class)
	public void getByIdWithErrorToAccessDatabase() {
		final Long taskId = 400L;
		doThrow(new RuntimeException()).when(this.taskRepository).findById(taskId);

		try {
			this.taskMysqlDatabaseGateway.getById(taskId);
		} catch (ErrorToAccessDatabaseGatewayException e) {
			assertEquals("challenger.error.database.access", e.code);
			assertEquals("Error to access database.", e.message);
			throw e;
		}
	}

}
