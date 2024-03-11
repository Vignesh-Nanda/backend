package com.example.vignesh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vignesh.model.User;



public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User>findByEmail(String email);
    
}
