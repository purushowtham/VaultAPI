package com.vaultapi.backend.controller;

import com.vaultapi.backend.dto.request.LoginRequest;
import com.vaultapi.backend.dto.request.RegisterRequest;
import com.vaultapi.backend.dto.response.ApiResponse;
import com.vaultapi.backend.dto.response.AuthResponse;
import com.vaultapi.backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ApiResponse<AuthResponse> register(

            @Valid

            @RequestBody

            RegisterRequest request

    ) {

        AuthResponse response =

                authService.register(request);

        return ApiResponse.<AuthResponse>builder()

                .success(true)

                .message("Registration successful")

                .data(response)

                .build();

    }

}
