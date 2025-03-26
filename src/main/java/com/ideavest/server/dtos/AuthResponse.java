package com.ideavest.server.dtos;

import com.ideavest.server.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String message;
    private User user;
}

