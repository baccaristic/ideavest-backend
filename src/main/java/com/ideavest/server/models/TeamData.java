package com.ideavest.server.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class TeamData {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String numberOfCofounders;
    private String teamDescription;
    private Boolean teamCapable;
    private String timeWorkingOnProject;
    private Boolean workedTogetherBefore;
    private Boolean launchedStartupBefore;
}