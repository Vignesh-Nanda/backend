package com.example.vignesh.dto.response;


import com.example.vignesh.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private String name;
    private String email;
    private String password;
    private String userRole;
   
}

