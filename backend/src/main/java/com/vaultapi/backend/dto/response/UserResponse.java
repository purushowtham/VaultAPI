package com.vaultapi.backend.dto.response;

import com.vaultapi.backend.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserResponse {

    private UUID id;

    private String fullName;

    private String email;

    private Role role;

}