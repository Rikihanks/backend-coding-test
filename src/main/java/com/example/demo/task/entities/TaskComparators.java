package com.example.demo.task.entities;


import java.util.Comparator;

public class TaskComparators {

    public static Comparator<TaskEntity> compareTaskByPriority = Comparator.comparing(TaskEntity::getPriority);
    public static Comparator<TaskEntity> compareTaskByCompletion = Comparator.comparing(TaskEntity::isCompleted);
    public static Comparator<TaskEntity> compareTaskByDescription = Comparator.comparing(TaskEntity::getDescription);

}
