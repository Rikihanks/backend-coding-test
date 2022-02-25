package com.example.demo.task.entities;


import org.hibernate.annotations.Cascade;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class SubtaskEntity extends TaskEntity {

    @OneToMany(cascade = CascadeType.ALL)
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
