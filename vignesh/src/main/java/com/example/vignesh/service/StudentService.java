package com.example.vignesh.service;

import java.util.List;
import java.util.Optional;

import com.example.vignesh.dto.request.InstitutionDTO;
import com.example.vignesh.dto.request.StudentDto;
import com.example.vignesh.model.Institution;
import com.example.vignesh.model.Students;

public interface StudentService {
    List<Students> getAllStudents();

    Students saveStudent(StudentDto student);

    Students saveStudents(Students students);

   

    Students getStudentById(int id);

    Students addStudent(Students student);

    void deleteStudent(int id);

    Students selectInstitution(int studentId, int institutionId);

    Students updateStudent(int id, Students student);

    // void addCourseToStudent(Students student, int courseId);

    // void addInstitutiontoStudent(int studentId,Institution institution);
}
