package com.ideavest.server.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Getter
@Setter
public class Notification {
    private String id = UUID.randomUUID().toString();
    private UUID userId;
    private String message;
    private String type; // INFO, WARNING, ERROR, SUCCESS
    private boolean read = false;
    private String link;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Notification(UUID userId, String message, String type, boolean read, String link) {
        this.userId = userId;
        this.message = message;
        this.type = type;
        this.read = read;
        this.link = link;
    }
}

