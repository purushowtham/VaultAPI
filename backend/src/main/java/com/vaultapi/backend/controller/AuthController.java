package com.vaultapi.backend.controller;

import com.vaultapi.backend.dto.request.LoginRequest;
import com.vaultapi.backend.dto.request.RegisterRequest;
import com.vaultapi.backend.dto.response.ApiResponse;
import com.vaultapi.backend.dto.response.AuthResponse;
import com.vaultapi.backend.dto.response.UserResponse;
import com.vaultapi.backend.service.AuthService;
import com.vaultapi.backend.util.ResponseUtil;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Register User
     */
    @PostMapping("/register")
    public ApiResponse<AuthResponse> register(
            @Valid
            @RequestBody
            RegisterRequest request
    ) {

        return ResponseUtil.success(

                "Registration successful",

                authService.register(request)

        );

    }

    /**
     * Login User
     */
    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(

            @Valid

            @RequestBody

            LoginRequest request

    ) {

        return ResponseUtil.success(

                "Login successful",

                authService.login(request)

        );

    }

    
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/me")
    public ApiResponse<UserResponse> currentUser(

            Authentication authentication

    ) {

        return ResponseUtil.success(

                "Current user",

                authService.getCurrentUser(

                        authentication.getName()

                )

        );

    }

}