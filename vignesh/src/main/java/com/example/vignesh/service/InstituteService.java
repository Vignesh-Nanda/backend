package com.example.vignesh.service;

import java.util.List;
import java.util.Set;

import com.example.vignesh.dto.request.InstitutionDTO;
import com.example.vignesh.model.Courses;
import com.example.vignesh.model.Institution;

public interface InstituteService {
    Institution saveInstitute(InstitutionDTO institute);

    Institution saveInstitution(Institution institute);
    
    Institution getInstituteById(int id);

    List<Institution> getAllInstitutes();

    void deleteInstitute(int id);

    Institution updateInstitute(int id, Institution updatedInstitute);

    void addCourseToInstitution(int institutionId, Courses course);

    void updateCourseInInstitution(int institutionId, Courses updatedCourse);

    void removeCourseFromInstitution(int institutionId, int courseId);

    Set<Courses> getCoursesByInstitution(int institutionId);
}
