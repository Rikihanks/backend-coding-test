package com.example.demo.task.controller;

import com.example.demo.task.dto.TaskDto;
import com.example.demo.task.entities.TaskPriority;
import com.example.demo.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class TaskController {

    @Autowired
    @Qualifier("TaskServiceV1")
    private TaskService taskService;

    @GetMapping("tasks")
    public List<TaskDto> getTasks(@RequestParam(value = "filter", required = false) Optional<String> filter,
                                  @RequestParam(value = "order", required = false) Optional<String> order) {
        return taskService.getTasks(filter, order);
    }

    @GetMapping("/tasks/{id}")
    public TaskDto getTaskById(@PathVariable("id") Integer id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("tasks")
    public TaskDto saveTask(@RequestBody TaskDto task) {
        return taskService.saveTask(task);
    }

    @PutMapping("/tasks/{id}")
    public TaskDto updateTask(@PathVariable("id") Integer taskId,
                                 @RequestParam(required = false) Optional<String> description,
                                 @RequestParam(required = false) Optional<Boolean> completed,
                                 @RequestParam(required = false) Optional<TaskPriority> priority) {
        return taskService.updateTask(taskId, description, completed, priority);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable("id") Integer taskId) {
        taskService.deleteTask(taskId);
    }

}
