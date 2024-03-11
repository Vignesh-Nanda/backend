package com.example.vignesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vignesh.model.Courses;

@Repository
public interface CourseRepo extends JpaRepository<Courses, Integer> {
}
