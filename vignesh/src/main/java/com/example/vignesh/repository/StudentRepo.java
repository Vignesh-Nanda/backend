package com.example.vignesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vignesh.model.Students;

public interface StudentRepo extends JpaRepository<Students, Integer> {
}
