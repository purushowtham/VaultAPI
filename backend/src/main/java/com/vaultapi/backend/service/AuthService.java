package com.vaultapi.backend.service;

import com.vaultapi.backend.dto.request.LoginRequest;
import com.vaultapi.backend.dto.request.RegisterRequest;
import com.vaultapi.backend.dto.response.AuthResponse;
import com.vaultapi.backend.dto.response.UserResponse;
import com.vaultapi.backend.entity.User;
import com.vaultapi.backend.exception.UserAlreadyExistsException;
import com.vaultapi.backend.security.jwt.JwtService;
import com.vaultapi.backend.security.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {

        if (userService.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException(
                    "Email already registered."
            );
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        user = userService.save(user);

        return buildAuthResponse(user);
    }

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userService.getByEmail(request.getEmail());

        return buildAuthResponse(user);
    }

    private AuthResponse buildAuthResponse(User user) {

        UserDetails userDetails = new CustomUserDetails(user);

        String token = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public UserResponse getCurrentUser(String email) {

        User user = userService.getByEmail(email);

        return UserResponse.builder()

                .id(user.getId())

                .fullName(user.getFullName())

                .email(user.getEmail())

                .role(user.getRole())

                .build();

    }

}