package com.example.demo.task.service;

import com.example.demo.exceptions.DataException;
import com.example.demo.task.dto.TaskDto;
import com.example.demo.task.entities.*;
import com.example.demo.task.mapper.TaskMapper;
import com.example.demo.task.repository.TaskRepository;
import com.example.demo.task.specification.TaskSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@Qualifier("TaskServiceV1")
public class TaskService implements ITaskService {

    @Autowired
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskDto getTaskById(int id) {
        var taskEntityOptional = taskRepository.findById(id);
        return taskEntityOptional.map(TaskMapper::convertToDto).orElse(null);
    }

    public List<TaskDto> getTasks(Optional<String> filter, Optional<String> order) {
        List<TaskEntity> tasks;
        Specification<TaskEntity> spec = null;

        if(filter.isPresent()) {
            TaskSpecificationBuilder builder = new TaskSpecificationBuilder();
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(filter.get() + ",");
            while (matcher.find()) {
                builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
            }
            spec = builder.build();
        }

        tasks = spec != null ? taskRepository.findAll(spec) : taskRepository.findAll();

        if(order.isPresent()) {
            switch (TaskOrderableFields.valueOf(order.get().toUpperCase())) {
                case PRIORITY:
                    tasks.sort(TaskComparators.compareTaskByPriority);
                    Collections.reverse(tasks);
                    break;
                case COMPLETED:
                    tasks.sort(TaskComparators.compareTaskByCompletion);
                    Collections.reverse(tasks);
                    break;
                case DESCRIPTION:
                    tasks.sort(TaskComparators.compareTaskByDescription);
                    break;
                case CREATIONDATE:
                    tasks.sort(TaskComparators.compareTaskByCreationDate);
            }
        }
         
        return TaskMapper.convertToDto(tasks);
    }

    public TaskDto saveTask(TaskDto taskDto) {
        var taskEntity = TaskMapper.convertToEntity(taskDto);
        var savedTask = taskRepository.save(taskEntity);
        return TaskMapper.convertToDto(savedTask);
    }

    @Transactional
    public TaskDto updateTask(int id, Optional<String> descriptionOptional, Optional<Boolean> completedOptional, Optional<TaskPriority> priorityOptional) {
        var taskEntityOptional = taskRepository.findById(id);
        if(taskEntityOptional.isEmpty()) {
            throw new DataException("Task with id " +id + " does not exist");
        }
        var taskEntity = taskEntityOptional.get();
        completedOptional.ifPresent(taskEntity::setCompleted);
        descriptionOptional.ifPresent(taskEntity::setDescription);
        priorityOptional.ifPresent(taskEntity::setPriority);
        return TaskMapper.convertToDto(taskEntity);
    }

    public void deleteTask(int id) {
        if(!taskRepository.existsById(id)) {
            throw new DataException("Task with id " +id + " does not exist");
        }
        taskRepository.deleteById(id);
    }
}
