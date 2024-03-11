package com.example.vignesh.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vignesh.dto.request.StudentDto;
import com.example.vignesh.model.Courses;
import com.example.vignesh.model.Institution;
import com.example.vignesh.model.Students;
import com.example.vignesh.service.CourseService;
import com.example.vignesh.service.InstituteService;
import com.example.vignesh.service.impl.Institute;
import com.example.vignesh.service.impl.StudentServ;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")

public class StudentCont {

    @Autowired
    private StudentServ studentService;

    @Autowired
    private Institute instituteService;

    @Autowired
    private CourseService courseService;

    @PostMapping("/add/student")
    public ResponseEntity<Students> createStudent(@RequestBody StudentDto student) {
        Students createdStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Students> getStudent(@PathVariable("id") int id) {
        Students student = studentService.getStudentById(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
    @GetMapping("/student")
    public ResponseEntity<List<Students>> getAllStudents() {
        List<Students> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") int id) {
        // Check if the student exists
        if (studentService.getStudentById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Delete the student
        studentService.deleteStudent(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/student/{id}")

    public ResponseEntity<Students> updateStudent(@PathVariable("id") int id, @RequestBody Students student) {
        Students existingStudent = studentService.updateStudent(id,student);
        if (existingStudent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(existingStudent, HttpStatus.OK);
    }

    @PostMapping("/{studentId}/institution/{institutionId}")
    public ResponseEntity<Students> selectInstitution(@PathVariable("studentId") int studentId,
            @PathVariable("institutionId") int institutionId) {
        Students student = studentService.getStudentById(studentId);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Institution institution = instituteService.getInstituteById(institutionId);
        if (institution == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        student.setInstitutions(institution);
        studentService.saveStudents(student);

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/{studentId}/institution/{institutionId}/course/{courseId}")
    public ResponseEntity<Students> selectInstitutionAndCourse(@PathVariable("studentId") int studentId,
            @PathVariable("institutionId") int institutionId,
            @PathVariable("courseId") int courseId) {
        Students student = studentService.getStudentById(studentId);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Institution institution = instituteService.getInstituteById(institutionId);
        if (institution == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Courses course = courseService.getCourseById(courseId);
        if (course == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Associate the course with the institution if necessary
        // if (!institution.getCourses().contains(course)) {
        // institution.getCourses().add(course);
        // instituteService.saveInstitution(institution);
        // }

        // Add the course to the student
        student.getCourses().add(course);
        studentService.saveStudents(student);

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

}
