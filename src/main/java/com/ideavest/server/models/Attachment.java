package com.ideavest.server.models;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Attachment {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String name;
    private String url;
    private String type;
    private Long size;

    @ManyToOne
    @JoinColumn(name = "idea_id")
    private Idea idea;
}