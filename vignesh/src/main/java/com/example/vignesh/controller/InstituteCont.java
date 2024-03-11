package com.example.vignesh.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.vignesh.dto.request.InstitutionDTO;
import com.example.vignesh.model.Courses;
import com.example.vignesh.model.Institution;
import com.example.vignesh.service.impl.CourseServ;
import com.example.vignesh.service.impl.Institute;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class InstituteCont {

    @Autowired
    private Institute instituteService;

    @Autowired
    private CourseServ courseService;

    // Endpoint to create institution
    @PostMapping("/add/institute")
    public ResponseEntity<Institution> createInstitute(@RequestBody InstitutionDTO institute) {
        Institution createdInstitute = instituteService.saveInstitute(institute);
        return new ResponseEntity<>(createdInstitute, HttpStatus.CREATED);
    }

    // Endpoint to retrieve institution by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Institution> getInstituteById(@PathVariable("id") int id) {
        Institution institute = instituteService.getInstituteById(id);
        if (institute == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(institute, HttpStatus.OK);
    }

    // Endpoint to retrieve all institutions
    @GetMapping("/get/institute")
    public ResponseEntity<List<Institution>> getAllInstitutes() {
        List<Institution> institutes = instituteService.getAllInstitutes();
        return new ResponseEntity<>(institutes, HttpStatus.OK);
    }

    // Endpoint to update institution
    @PutMapping("/put/{id}")
    public ResponseEntity<Institution> updateInstitute(@PathVariable int id,
            @RequestBody Institution updatedInstitute) {
        Institution institute = instituteService.updateInstitute(id, updatedInstitute);
        if (institute != null) {
            return new ResponseEntity<>(institute, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to delete institution by ID
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteInstitute(@PathVariable("id") int id) {
        instituteService.deleteInstitute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint to add course to institution
    @PostMapping("/{institutionId}/courses/{courseId}")
    public ResponseEntity<Void> addCourseToInstitution(@PathVariable int institutionId, @PathVariable int courseId) {
        Institution institution = instituteService.getInstituteById(institutionId);
        if (institution == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Courses course = courseService.getCourseById(courseId); // Assuming courseService is an instance of CourseServ
        if (course == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        institution.getCourses().add(course);
        instituteService.saveInstitution(institution);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Endpoint to update course in institution
    @PutMapping("/{institutionId}/courses/{courseId}")
    public ResponseEntity<Void> updateCourseInInstitution(@PathVariable int institutionId, @PathVariable int courseId,
            @RequestBody Courses updatedCourse) {
        instituteService.updateCourseInInstitution(institutionId, updatedCourse);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Endpoint to remove course from institution
    @DeleteMapping("/{institutionId}/courses/{courseId}")
    public ResponseEntity<Void> removeCourseFromInstitution(@PathVariable int institutionId,
            @PathVariable int courseId) {
        instituteService.removeCourseFromInstitution(institutionId, courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint to get courses by institution ID
    @GetMapping("/{institutionId}/courses")
    public ResponseEntity<Set<Courses>> getCoursesByInstitution(@PathVariable int institutionId) {
        Set<Courses> courses = instituteService.getCoursesByInstitution(institutionId);
        if (courses != null) {
            return ResponseEntity.ok(courses);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
