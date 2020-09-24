package br.com.accenture.challenger.usecases;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.apache.commons.lang3.ClassUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
		when(this.taskService.getAllTasks()).thenReturn(tasks);
		
		final List<Task> tasksResponse = this.taskService.getAllTasks();
		
		assertEquals(tasks.get(0).getDate(), tasksResponse.get(0).getDate());
		assertEquals(tasks.get(0).getDescription(), tasksResponse.get(0).getDescription());
		assertEquals(tasks.get(0).getId(), tasksResponse.get(0).getId());
		assertEquals(tasks.get(0).getIsDone(), tasksResponse.get(0).getIsDone());
		
		assertEquals(tasks.get(1).getDate(), tasksResponse.get(1).getDate());
		assertEquals(tasks.get(1).getDescription(), tasksResponse.get(1).getDescription());
		assertEquals(tasks.get(1).getId(), tasksResponse.get(1).getId());
		assertEquals(tasks.get(1).getIsDone(), tasksResponse.get(1).getIsDone());	
	}

}
