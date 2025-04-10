package com.ideavest.server.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class ProgressData {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String projectStage;
    private String currentProgress;
    private Boolean joinedIncubator;
    private Boolean wonEntrepreneurshipAward;
    private Boolean filedPatents;
}