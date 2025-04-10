
package com.ideavest.server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Estimation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private Double price;

    @Column(length = 1000)
    private String notes;

    @ManyToOne
    private User estimatedBy;

    @OneToOne
    @JsonIgnore
    private Idea idea;
}
