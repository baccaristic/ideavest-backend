package com.ideavest.server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "idea_additional_data")
public class IdeaAdditionalData {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String sector;
    private String technology;
    private String region;
    private String website;

    @OneToOne
    @JoinColumn(name = "idea_id")
    @JsonIgnore
    private Idea idea;

    @OneToOne(cascade = CascadeType.ALL)
    private InnovationData innovation;

    @OneToOne(cascade = CascadeType.ALL)
    private MarketData market;

    @OneToOne(cascade = CascadeType.ALL)
    private ProgressData progress;

    @OneToOne(cascade = CascadeType.ALL)
    private TeamData team;

    @OneToOne(cascade = CascadeType.ALL)
    private PresentationData presentation;

    @OneToOne(cascade = CascadeType.ALL)
    private FundingData funding;

    @OneToOne(cascade = CascadeType.ALL)
    private AdditionalInfo additional;

    @OneToMany(cascade = CascadeType.ALL)
    private List<DocumentData> documents = new ArrayList<>();
}