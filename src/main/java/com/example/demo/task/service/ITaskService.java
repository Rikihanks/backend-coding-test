package com.example.demo.task.service;

import com.example.demo.task.TaskDto;
import com.example.demo.task.entities.TaskPriority;

import java.util.List;
import java.util.Optional;

public interface ITaskService {

    TaskDto getTaskById(int id);

    List<TaskDto> getTasks(Optional<String> search, Optional<String> filter);

    TaskDto saveTask(TaskDto taskDto);

    TaskDto updateTask(int id, Optional<String> descriptionOptional, Optional<Boolean> completedOptional, Optional<TaskPriority> priorityOptional);

    void deleteTask(int id);
}
