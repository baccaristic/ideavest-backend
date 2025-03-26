package com.ideavest.server.dtos;

import com.ideavest.server.models.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private UserRole role;
    private String avatarUrl;
}

