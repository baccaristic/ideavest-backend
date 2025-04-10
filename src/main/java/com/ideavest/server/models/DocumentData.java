package com.ideavest.server.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class DocumentData {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String name;
    private String type;
    private Long size;
    private String description;
}