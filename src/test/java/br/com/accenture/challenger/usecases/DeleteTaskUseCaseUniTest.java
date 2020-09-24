package br.com.accenture.challenger.usecases;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

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
import br.com.accenture.challenger.gateways.database.task.TaskDatabaseGateway;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class DeleteTaskUseCaseUniTest {

	@InjectMocks
	private DeleteTaskUseCase deleteTaskUseCase;

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
	public void deleteWithSuccess() {
		final Task task = Fixture.from(Task.class).gimme(TaskTemplate.GET_TASK);

		this.deleteTaskUseCase.delete(task);

		final ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
		
		verify(this.taskDatabaseGateway, VerificationModeFactory.times(1)).delete(taskCaptor.capture());	

		final Task taskCaptured = taskCaptor.getValue();
		assertEquals(task.getDate(), taskCaptured.getDate());
		assertEquals(task.getDescription(), taskCaptured.getDescription());
		assertEquals(task.getId(), taskCaptured.getId());
		assertEquals(task.getIsDone(), taskCaptured.getIsDone());
	}
	
}
