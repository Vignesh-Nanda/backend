package com.example.vignesh.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vignesh.model.AdminModel;
import com.example.vignesh.model.User;
import com.example.vignesh.repository.AdminRepo;
import com.example.vignesh.repository.UserRepo;

@Service
public class AdminService {
    @Autowired
    AdminRepo adminRepo;
    @Autowired
    UserRepo userRepo;

    public Optional<AdminModel> getAdmin(int id) {
        return adminRepo.findById(id);
    }

    public String updateAdmin(AdminModel adminModel) {
        Optional<User> user = userRepo.findByEmail(adminModel.getUser().getEmail());
        if (user.isEmpty()) {
            return "User not found";
        }
        user.get().setName(adminModel.getUser().getName());
        userRepo.save(user.get());
        Optional<AdminModel> admin = adminRepo.findByUser(user.get());
        admin.get().setMobileNumber(adminModel.getMobileNumber());
        adminRepo.save(admin.get());
        return "Updated successfully";
    }

}
