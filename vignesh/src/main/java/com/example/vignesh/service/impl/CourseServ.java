package com.example.vignesh.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vignesh.model.Courses;
import com.example.vignesh.repository.CourseRepo;
import com.example.vignesh.service.CourseService;

import java.util.List;

@Service
public class CourseServ implements CourseService {

    @Autowired
    private CourseRepo courseRepository;

    @Override
    public List<Courses> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Courses getCourseById(int courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }
    

    @Override
    public Courses createCourse(Courses course) {
        return courseRepository.save(course);
    }
    public Courses updateCourse(int courseId, Courses updatedCourse) {
        Courses existingCourse = courseRepository.findById(courseId).orElse(null);
        if (existingCourse != null) {
            if (updatedCourse.getCourseName() != null) {
                existingCourse.setCourseName(updatedCourse.getCourseName());
            }
            if (updatedCourse.getCourseDescription() != null) {
                existingCourse.setCourseDescription(updatedCourse.getCourseDescription());
            }
            if (updatedCourse.getCourseDuration() != 0) {
                existingCourse.setCourseDuration(updatedCourse.getCourseDuration());
            }
            return courseRepository.save(existingCourse);
        }
        return null;
    }

    @Override
    public void deleteCourse(int courseId) {
        courseRepository.deleteById(courseId);
    }
}

