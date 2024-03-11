package com.example.vignesh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vignesh.dto.request.InstitutionDTO;
import com.example.vignesh.model.Courses;
import com.example.vignesh.model.Institution;
import com.example.vignesh.repository.InstituteRepo;
import com.example.vignesh.service.InstituteService;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class Institute implements InstituteService {

    @Autowired
    private InstituteRepo instituteRepository;

    @Autowired
    private CourseServ courseService;

    @Override
    public Institution saveInstitute(InstitutionDTO institute) {
        Institution i = new Institution();
        i.setEmail(institute.getEmail());
        i.setInstituteAddress(institute.getInstituteAddress());
        i.setInstituteDescription(institute.getInstituteDescription());
        i.setInstituteId(institute.getInstituteId());
        i.setInstituteName(institute.getInstituteName());
        i.setMobile(institute.getMobile());
        i.setCourses(new HashSet<>());
        return instituteRepository.save(i);
    }

    @Override
    public Institution saveInstitution(Institution institution) {
        return instituteRepository.save(institution);
    }

    @Override
    public Institution getInstituteById(int id) {
        return instituteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Institution> getAllInstitutes() {
        return instituteRepository.findAll();
    }

    @Override
    public Institution updateInstitute(int id, Institution updatedInstitute) {
        Institution existingInstitute = instituteRepository.findById(id).orElse(null);
        if (existingInstitute != null) {
            existingInstitute.setInstituteName(updatedInstitute.getInstituteName());
            existingInstitute.setInstituteDescription(updatedInstitute.getInstituteDescription());
            existingInstitute.setInstituteAddress(updatedInstitute.getInstituteAddress());
            existingInstitute.setMobile(updatedInstitute.getMobile());
            existingInstitute.setEmail(updatedInstitute.getEmail());
            return instituteRepository.save(existingInstitute);
        } else {
            return null; // or throw an exception indicating the institute was not found
        }
    }

    @Override
    public void deleteInstitute(int id) {
        instituteRepository.deleteById(id);
    }

    @Override
    public void addCourseToInstitution(int institutionId, Courses course) {
        Institution institution = instituteRepository.findById(institutionId).orElse(null);
        if (institution != null) {
            institution.getCourses().add(course);
            instituteRepository.save(institution);
        }
    }

    @Override
    public void updateCourseInInstitution(int institutionId, Courses updatedCourse) {
        // Implement update course logic
    }

    @Override
    public void removeCourseFromInstitution(int institutionId, int courseId) {
        Institution institution = instituteRepository.findById(institutionId).orElse(null);
        if (institution != null) {
            institution.getCourses().removeIf(course -> course.getCourseId() == courseId);
            instituteRepository.save(institution);
        }
    }

    @Override
    public Set<Courses> getCoursesByInstitution(int institutionId) {
        Institution institution = instituteRepository.findById(institutionId).orElse(null);
        if (institution != null) {
            return institution.getCourses();
        }
        return Collections.emptySet();
    }

   
}
