package com.example.vignesh.service.impl;

import static io.swagger.v3.oas.models.security.SecurityScheme.Type.valueOf;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.vignesh.dto.request.AuthenticationRequest;
import com.example.vignesh.dto.request.RegisterRequest;
import com.example.vignesh.dto.response.AuthenticationResponse;
import com.example.vignesh.model.AdminModel;
import com.example.vignesh.model.Role;
import com.example.vignesh.model.User;
import com.example.vignesh.model.UserModel;
import com.example.vignesh.repository.AdminRepo;
import com.example.vignesh.repository.UserModelRepo;
import com.example.vignesh.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
        private final UserModelRepo userModelRepository;
        private final AdminRepo adminModelRepository;
        private final UserRepo userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthenticationResponse register(RegisterRequest request) {
                if (request.getUserRole().equals("user")) {
                        var user = User
                                        .builder()
                                        .name(request.getName())
                                        .email(request.getEmail())
                                        .password(passwordEncoder.encode(request.getPassword()))
                                        .role(Role.USER)
                                        .build();
                        Optional<User> existing = userRepository.findByEmail(request.getEmail());
                        if (!existing.isEmpty()) {
                                return AuthenticationResponse.builder()
                                                .token("Email Already exists")
                                                .build();
                                                
                        }
                        User savedUser = userRepository.save(user);
                        UserModel userModel = new UserModel();
                        userModel.setMobileNumber(request.getMobileNumber());
                        userModel.setUser(savedUser);
                        userModelRepository.save(userModel);
                        var jwtToken = jwtService.generateToken(user);

                        return AuthenticationResponse.builder()
                                        .token(jwtToken)

                                        .build();
                }
                var user = User
                                .builder()
                                .name(request.getName())
                                .email(request.getEmail())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .role(Role.ADMIN)
                                .build();
                Optional<User> existing = userRepository.findByEmail(request.getEmail());
                if (!existing.isEmpty()) {
                        return AuthenticationResponse.builder()
                                        .token("Email Already exists")
                                        .build();
                }
                User savedUser = userRepository.save(user);
                AdminModel adminModel = new AdminModel();
                adminModel.setMobileNumber(request.getMobileNumber());
                adminModel.setUser(savedUser);
                adminModelRepository.save(adminModel);
                var jwtToken = jwtService.generateToken(user);

                return AuthenticationResponse.builder()
                                .token(jwtToken)

                                .build();
        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
                var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
              
                var jwtToken = jwtService.generateToken(user);
                var userRole = (user.getRole() == Role.ADMIN) ? "admin" : "user";
                return AuthenticationResponse.builder()
                                .token(jwtToken)
                                // .userRole(userRole)
                                .name(user.getName())
                                .email(user.getEmail())
                                .password(user.getPassword())
                                .userRole(userRole)
                                .build();
        }
}
