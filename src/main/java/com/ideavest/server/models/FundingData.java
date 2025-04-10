package com.ideavest.server.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class FundingData {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String fundraisingGoal;
    private String revenueProjection;
}