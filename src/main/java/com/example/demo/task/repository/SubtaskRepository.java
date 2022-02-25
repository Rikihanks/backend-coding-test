package com.example.demo.task.repository;

import com.example.demo.task.entities.SubtaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtaskRepository extends JpaRepository<SubtaskEntity, Integer> {

}