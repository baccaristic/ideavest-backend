package com.ideavest.server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Idea {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String title;
    private String description;
    private String category;

    @Enumerated(EnumType.STRING)
    private IdeaStatus status;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private User owner;


    @ManyToOne
    @JoinColumn(name = "assignedTo_id")
    @JsonIgnore
    private User assignedTo;

    private String submittedBy;
    private String submitterName;

    private Double estimatedBudget;
    private Double estimatedPrice;

    private Integer views;
    private Integer likes;

    @ElementCollection
    private List<String> tags;

    @OneToMany(mappedBy = "idea", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attachment> attachments = new ArrayList<>();

    @OneToMany(mappedBy = "idea", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToOne(mappedBy = "idea", cascade = CascadeType.ALL, orphanRemoval = true)
    private IdeaAdditionalData additionalData;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Estimation estimation;

    private Instant createdAt;
    private Instant updatedAt;
}