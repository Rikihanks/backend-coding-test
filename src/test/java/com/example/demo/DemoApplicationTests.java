package com.example.demo;

import com.example.demo.task.entities.SubtaskEntity;
import com.example.demo.task.entities.TaskEntity;
import com.example.demo.task.entities.TaskPriority;
import com.example.demo.task.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class DemoApplicationTests {


	@Autowired
	TaskRepository repository;


	private TaskEntity taskToSave = new TaskEntity();

	@BeforeEach
	public void setUp() {
		/*MockitoAnnotations.initMocks(this);

		TaskEntity task = new TaskEntity();
		task.setCompleted(false);
		task.setDescription("Test description");
		task.setPriority(TaskPriority.MEDIUM);
		task.setId(1);

		TaskEntity task2 = new TaskEntity();
		task2.setCompleted(true);
		task2.setDescription("z description");
		task2.setPriority(TaskPriority.HIGH);
		task2.setId(2);

		TaskEntity task3 = new TaskEntity();
		task3.setCompleted(true);
		task3.setDescription("a description");
		task3.setPriority(TaskPriority.LOW);
		task3.setId(3);

		when(repository.findAll()).thenReturn(Arrays.asList(task));
		when(repository.findById(1)).thenReturn(Optional.of(task));
		when(repository.save(taskToSave)).thenReturn(taskToSave);
		repository.saveAll(Arrays.asList(task, task2, task3));*/
	}


	@Test
	void shouldSaveTask() {
		TaskEntity task = new TaskEntity();
		task.setCompleted(false);
		task.setDescription("Test description");
		task.setPriority(TaskPriority.MEDIUM);
		var taskSaved = repository.save(task);
		assertThat(taskSaved).isNotNull();
	}

	@Test
	void shouldSaveSubtask() {
		TaskEntity task2 = new TaskEntity();
		task2.setCompleted(true);
		task2.setDescription("z description");
		task2.setPriority(TaskPriority.HIGH);
		task2.setId(2);

		TaskEntity subtaskEntity = new SubtaskEntity(List.of(task2));
		subtaskEntity.setCompleted(false);
		subtaskEntity.setDescription("Test description");
		subtaskEntity.setPriority(TaskPriority.MEDIUM);
		var taskSaved = repository.save(subtaskEntity);

		assertThat(((SubtaskEntity) taskSaved).getTasks().size()).isGreaterThan(0);
		assertThat(taskSaved).isNotNull();
	}

	/*@Test
	void whenAskingForAllTasks_thenTasksShouldBeFound() {
		var tasks = taskService.getTasks(Optional.ofNullable(null),Optional.ofNullable(null));
		assertThat(tasks.size()).isGreaterThan(0);
	}

	@Test
	void whenAskingForTaskWithId_thenTaskWithIdShouldBeFound() {
		var task = taskService.getTaskById(1);
		assertThat(task).isNotNull();
	}

	@Test
	void whenSavingANewTask_thenTaskShouldBeSavedAndReturned() {
		taskToSave.setPriority(TaskPriority.HIGH);
		taskToSave.setDescription("Task to save");
		taskToSave.setCompleted(false);
		//taskToSave.setId(16);
		TaskDto dto = new TaskDto();
		dto.setPriority(TaskPriority.HIGH);
		dto.setDescription("Task to save");
		dto.setCompleted(false);
		var task = taskService.saveTask(dto);
		assertThat(task).isNotNull();
	}*/
}
