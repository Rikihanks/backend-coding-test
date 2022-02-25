package com.example.demo;

import com.example.demo.task.entities.SubtaskEntity;
import com.example.demo.task.entities.TaskEntity;
import com.example.demo.task.entities.TaskPriority;
import com.example.demo.task.mapper.TaskMapper;
import com.example.demo.task.repository.TaskRepository;
import com.example.demo.task.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class DemoApplicationTests {

	@Autowired
	TaskRepository repository;

	@BeforeEach
	public void setUp() {
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
	void shouldRetrieveAllTasksFromDb() {
		TaskEntity task = new TaskEntity();
		task.setCompleted(false);
		task.setDescription("Test description");
		task.setPriority(TaskPriority.MEDIUM);
		var taskSaved = repository.save(task);

		var tasks = repository.findAll();
		assertThat(tasks.size()).isEqualTo(1);
	}

	@Test
	void shouldSaveTaskWithSubtask() {
		TaskEntity task2 = new TaskEntity();
		SubtaskEntity subtaskEntity = new SubtaskEntity();
		subtaskEntity.setCompleted(false);
		subtaskEntity.setDescription("Test description");
		subtaskEntity.setPriority(TaskPriority.MEDIUM);
		subtaskEntity.setTask(task2);

		task2.setCompleted(true);
		task2.setDescription("z description");
		task2.setPriority(TaskPriority.HIGH);
		task2.setSubTasks(List.of(subtaskEntity));

		var taskSaved = repository.save(task2);
		assertThat(taskSaved).isNotNull();
		assertThat(taskSaved.getSubTasks().size()).isGreaterThan(0);
	}

	@Test
	void shouldUpdateTask() {

		TaskEntity task2 = new TaskEntity();
		SubtaskEntity subtaskEntity = new SubtaskEntity();
		subtaskEntity.setCompleted(false);
		subtaskEntity.setDescription("Test description");
		subtaskEntity.setPriority(TaskPriority.MEDIUM);

		var taskSaved = repository.save(task2);

		var taskOptional = repository.findById(taskSaved.getId());
		if(taskOptional.isPresent()) {
			var task = taskOptional.get();
			task.setCompleted(true);
			task = repository.save(task);
			assertThat(task.isCompleted()).isTrue();
		}

	}

	@Test
	void shouldDeleteTask() {
		TaskEntity task2 = new TaskEntity();
		SubtaskEntity subtaskEntity = new SubtaskEntity();
		subtaskEntity.setCompleted(false);
		subtaskEntity.setDescription("Test description");
		subtaskEntity.setPriority(TaskPriority.MEDIUM);

		var taskSaved = repository.save(task2);

		repository.deleteById(taskSaved.getId());
		var taskOptional = repository.findById(taskSaved.getId());
		assertThat(taskOptional.isPresent()).isFalse();
	}

	@Test
	void shouldDeleteTaskWithSubtasks() {
		TaskEntity task2 = new TaskEntity();
		SubtaskEntity subtaskEntity = new SubtaskEntity();
		subtaskEntity.setCompleted(false);
		subtaskEntity.setDescription("Test description");
		subtaskEntity.setPriority(TaskPriority.MEDIUM);
		subtaskEntity.setTask(task2);

		task2.setCompleted(true);
		task2.setDescription("z description");
		task2.setPriority(TaskPriority.HIGH);
		task2.setSubTasks(List.of(subtaskEntity));

		var taskSaved = repository.save(task2);

		repository.deleteById(taskSaved.getId());
		var taskOptional = repository.findById(taskSaved.getId());
		assertThat(taskOptional.isPresent()).isFalse();
	}

}
