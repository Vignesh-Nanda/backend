package com.example.vignesh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vignesh.model.User;
import com.example.vignesh.model.UserModel;

public interface UserModelRepo extends JpaRepository<UserModel,Integer> {
     Optional<UserModel> findByUser(User user);
    Optional<UserModel> findByUserEmail(String email);
}
