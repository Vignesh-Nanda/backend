package com.example.vignesh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.vignesh.model.Courses;
import com.example.vignesh.service.CourseService;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class Coursescont {
    @Autowired
    private CourseService courseService;

    @GetMapping("/get/course")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Courses>> getAllCourses() {
        List<Courses> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{courseId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Courses> getCourseById(@PathVariable int courseId) {
        Courses course = courseService.getCourseById(courseId);
        if (course != null) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Courses> createCourse(@RequestBody Courses course) {
        Courses createdCourse = courseService.createCourse(course);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }
    
    @PutMapping("/update/{courseId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Courses> updateCourse(@PathVariable int courseId, @RequestBody Courses updatedCourse) {
        Courses course = courseService.updateCourse(courseId, updatedCourse);
        if (course != null) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{courseId}")
    @PreAuthorize("hasAuthority('ADMIN')")  
    public ResponseEntity<Void> deleteCourse(@PathVariable int courseId) {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}