package com.example.vignesh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf
                        .disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/v1/auth/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html/",
                                "/admin/**",
                                "/user/**",
                                "/v3/api-docs/**")
                        .permitAll())
                // All other request need to be authentic
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/v1/auth/add/")
                        .authenticated())
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider) // 1. Here we need to tell spring which authentication
                // provider we are going to use
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // 2. Adding a
                                                                                             // jwtAuthFilter
                                                                                             // before
                                                                                             // UsernamePasswordAuthenticationFilter
        return httpSecurity.build();
    }
}