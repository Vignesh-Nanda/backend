package com.example.vignesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.vignesh.model.Institution;



public interface InstituteRepo extends JpaRepository<Institution, Integer> {
    // You can add custom query methods here if needed
}
