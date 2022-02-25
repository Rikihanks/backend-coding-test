package com.example.demo.task.mapper;

import com.example.demo.task.dto.TaskDto;
import com.example.demo.task.entities.TaskEntity;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;


public class TaskMapper {

    private static ModelMapper modelMapper;

    private static ModelMapper getModelMapperInstance() {
        if(modelMapper == null) {
            modelMapper = new ModelMapper();
        }
        return modelMapper;
    }

    public static TaskEntity convertToEntity(TaskDto taskDto) {
        return getModelMapperInstance().map(taskDto, TaskEntity.class);
    }

    public static List<TaskEntity> convertToEntity(List<TaskDto> taskDtoList) {
        List<TaskEntity> taskEntities = new ArrayList<>();
        taskDtoList.forEach(taskDto -> taskEntities.add(convertToEntity(taskDto)));
        return taskEntities;
    }

    public static List<TaskDto> convertToDto(List<TaskEntity> taskEntityList) {
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskEntityList.forEach(taskEntity -> taskDtoList.add(convertToDto(taskEntity)));
        return taskDtoList;
    }

    public static TaskDto convertToDto(TaskEntity taskEntity) {
        return getModelMapperInstance().map(taskEntity, TaskDto.class);
    }
}
