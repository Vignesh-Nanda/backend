package com.example.vignesh.dto.request;

import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private int studentId;
    private String studentName;
    private String studentDOB;
    private String address;
    private String mobile;
    private int age;
    // private InstitutionDTO institutions;
    // Getters and setters
    

}
