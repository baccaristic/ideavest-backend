package com.ideavest.server.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String url;
    private String type;
    private Long size;

    @ManyToOne
    @JoinColumn(name = "idea_id")
    private Idea idea;
}

