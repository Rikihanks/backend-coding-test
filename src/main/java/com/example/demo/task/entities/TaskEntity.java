package com.example.demo.task.entities;

import javax.persistence.*;

@Entity
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class TaskEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private int id;

    private String description;
    private boolean completed;
    private TaskPriority priority;

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


}
