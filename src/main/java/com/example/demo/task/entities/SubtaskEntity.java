package com.example.demo.task.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class SubtaskEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private int id;

    private String description;
    private boolean completed;
    private TaskPriority priority;
    private Date creationDate;
    @ManyToOne()
    @JoinColumn(name="task_id", nullable = false)
    private TaskEntity task;

    public SubtaskEntity() {

    }

    public SubtaskEntity(int id, String description, boolean completed, TaskPriority priority, TaskEntity task) {
        this.id = id;
        this.description = description;
        this.completed = completed;
        this.priority = priority;
        this.task = task;
    }

    @PrePersist
    public void prePersist() {
        if(creationDate == null) creationDate = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

}
