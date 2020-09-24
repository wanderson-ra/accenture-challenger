package br.com.accenture.challenger.usecases;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;

import br.com.accenture.challenger.gateways.database.task.TaskDatabaseGateway;

public class DeleteTaskUseCaseUniTest {

	@InjectMocks
	private DeleteTaskUseCase deleteTaskUseCase;

	@Mock
	private TaskDatabaseGateway taskDatabaseGateway;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	
	@Test
	public void deleteWithSuccess() {
		final Long taskId = 300L;

		this.deleteTaskUseCase.delete(taskId);

		verify(this.taskDatabaseGateway, VerificationModeFactory.times(1)).delete(taskId);
	}
	
}
