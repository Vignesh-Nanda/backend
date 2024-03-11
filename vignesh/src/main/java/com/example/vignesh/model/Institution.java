package com.example.vignesh.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "institute")
@Getter
@Setter
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int instituteId;
    private String instituteName;
    private String instituteDescription;
    private String instituteAddress;
    private String mobile;
    private String email;

    @ManyToMany
    @JoinTable(name = "institution_course", joinColumns = @JoinColumn(name = "institute_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Courses> courses = new HashSet<>();

    // @ManyToMany(mappedBy = "institutions")
    // private Set<Students> students = new HashSet<>();
    // Constructors, getters, and setters
    
}
