package com.example.demo.task.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class TaskEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private int id;

    private String description;
    private boolean completed;
    private TaskPriority priority;
    private Date creationDate;

    @OneToMany(mappedBy="task", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<SubtaskEntity> subTasks = new ArrayList<>();


    @PrePersist
    public void prePersist() {
        if(creationDate == null) creationDate = new Date();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public List<SubtaskEntity> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubtaskEntity> subTasks) {
        this.subTasks = subTasks;
        if(subTasks != null && subTasks.size() > 0) {
            subTasks.forEach((s)->s.setTask(this));
        }
    }

}
