package com.example.vignesh.service;

import java.util.List;

import com.example.vignesh.model.Courses;

public interface CourseService {
    List<Courses> getAllCourses();
    Courses getCourseById(int courseId);
    Courses createCourse(Courses course);
    Courses updateCourse(int courseId, Courses updatedCourse);
    void deleteCourse(int courseId);
}

