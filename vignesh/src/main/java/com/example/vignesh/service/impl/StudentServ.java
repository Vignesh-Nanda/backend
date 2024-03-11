package com.example.vignesh.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vignesh.dto.request.StudentDto;
import com.example.vignesh.model.Courses;
import com.example.vignesh.model.Institution;
import com.example.vignesh.model.Students;
import com.example.vignesh.repository.StudentRepo;
import com.example.vignesh.service.CourseService;
import com.example.vignesh.service.InstituteService;
import com.example.vignesh.service.StudentService;

@Service
public class StudentServ implements StudentService {
    @Autowired
    private StudentRepo studentRepository;

    @Autowired
    private InstituteService instituteService;

    @Autowired
    private CourseService courseService;

    @Override
    public List<Students> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Students saveStudent(StudentDto studentDto) {
        Students student = mapDtoToEntity(studentDto);
        return studentRepository.save(student);
    }
   

    @Override
    public Students updateStudent(int id, Students student) {
        // Check if the student exists
        Students existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent == null) {
            return null;
        }

        // Update student details
        existingStudent.setStudentName(student.getStudentName());
        existingStudent.setStudentDOB(student.getStudentDOB());
        existingStudent.setAddress(student.getAddress());
        existingStudent.setMobile(student.getMobile());
        existingStudent.setAge(student.getAge());

        // Save and return the updated student
        return studentRepository.save(existingStudent);
    }

    @Override
    public Students saveStudents(Students students) {
        return studentRepository.save(students);
    }

    @Override
    public Students getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Students addStudent(Students student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    // Change access modifier to package-private or public

    private Students mapDtoToEntity(StudentDto studentDto) {
        Students student = new Students();
        // Map fields from DTO to entity
        student.setStudentId(studentDto.getStudentId());
        student.setStudentName(studentDto.getStudentName());
        student.setStudentDOB(studentDto.getStudentDOB());
        student.setAddress(studentDto.getAddress());
        student.setMobile(studentDto.getMobile());
        student.setAge(studentDto.getAge());
        // student.setCourses(new HashSet<>());
        // student.setInstitutions(new HashSet<>());
        return student;
    }

    @Override
    public Students selectInstitution(int studentId, int institutionId) {
        Students student = getStudentById(studentId);
        if (student == null) {
            return null;
        }

        Institution institution = instituteService.getInstituteById(institutionId);
        if (institution == null) {
            return null;
        }

        student.setInstitutions(institution);
        return saveStudents(student);
    }

}
