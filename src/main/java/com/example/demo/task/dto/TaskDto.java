package com.example.demo.task.dto;

import com.example.demo.task.entities.TaskPriority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private int id;
    private String description;
    private boolean completed;
    private TaskPriority priority;
    private List<TaskDto> subTasks;
    private Date creationDate;
}
