package com.ideavest.server.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "idea_id", nullable = false)
    private Idea idea;

    @ManyToOne
    @JoinColumn(name = "investor_id", nullable = false)
    private User investor;

    @Enumerated(EnumType.STRING)
    private AgreementStatus status = AgreementStatus.PENDING;

    @Lob
    private String signatureData; // Base64 encoded signature

    private LocalDateTime signedAt;
    private LocalDateTime createdAt = LocalDateTime.now();
}

