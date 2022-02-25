package com.example.demo.task.entities;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Entity
public class SubtaskEntity extends TaskEntity {

    @ElementCollection
    private List<TaskEntity> tasks;

    public SubtaskEntity(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }

    public SubtaskEntity() {
        super();
    }

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }
}
