package com.example.vignesh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vignesh.model.AdminModel;
import com.example.vignesh.model.User;

public interface AdminRepo extends JpaRepository<AdminModel,Integer> {
   Optional <AdminModel>  findByUser(User user);
   Optional <AdminModel> findByUserEmail(String email);
}
