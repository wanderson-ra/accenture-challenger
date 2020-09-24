package br.com.accenture.challenger.usecases;

import org.apache.commons.lang3.ClassUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import br.com.accenture.challenger.databuilders.domains.DomainsTemplateLoader;
import br.com.accenture.challenger.databuilders.domains.TaskTemplate;
import br.com.accenture.challenger.domains.Task;
import br.com.accenture.challenger.gateways.database.task.TaskDatabaseGateway;
import br.com.accenture.challenger.usecases.exceptions.TaskNotFountBusinessException;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class GetTaskByIdUseCaseUniTest {

	@InjectMocks
	private GetTaskByIdUseCase getTaskByIdUseCase;

	@Mock
	private TaskDatabaseGateway taskDatabaseGateway;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeClass
	public static void init() {
		FixtureFactoryLoader.loadTemplates(ClassUtils.getPackageName(DomainsTemplateLoader.class));
	}

	@Test
	public void getWithSuccess() {
		final Task task = Fixture.from(Task.class).gimme(TaskTemplate.GET_TASK);
		final Long taskId = 200L;

		when(this.taskDatabaseGateway.getById(taskId)).thenReturn(Optional.of(task));

		final Task taskResponse = this.getTaskByIdUseCase.get(taskId);

		assertEquals(task.getDate(), taskResponse.getDate());
		assertEquals(task.getDescription(), taskResponse.getDescription());
		assertEquals(task.getId(), taskResponse.getId());
		assertEquals(task.getIsDone(), taskResponse.getIsDone());
	}

	@Test(expected = TaskNotFountBusinessException.class)
	public void getWithTaskNotFound() {
		final Long taskId = 200L;

		when(this.taskDatabaseGateway.getById(taskId)).thenReturn(Optional.empty());

		try {
			
			this.getTaskByIdUseCase.get(taskId);
			
		} catch (TaskNotFountBusinessException error) {
			assertEquals("challenger.error.taskNotFound", error.code);
			assertEquals("Task Not Found.", error.message);
			throw error;
		}

		
	}
}
