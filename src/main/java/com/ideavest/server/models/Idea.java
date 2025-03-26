package com.ideavest.server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Idea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;
    private String description;
    private String category;
    @Enumerated(EnumType.STRING)
    private IdeaStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Double estimatedBudget;
    private Double estimatedPrice;
    private Integer views;
    private Integer likes;

    @ElementCollection
    private List<String> tags;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private User owner;

    @ManyToOne
    @JoinColumn(name = "submitted_by")
    private User submittedBy;

    @OneToMany(mappedBy = "idea", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "idea", cascade = CascadeType.ALL)
    private List<Attachment> attachments;
}
