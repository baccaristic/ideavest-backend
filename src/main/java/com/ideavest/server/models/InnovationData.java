package com.ideavest.server.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class InnovationData {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String projectDescription;
    private String problemStatement;
    private String solution;
    private String innovationFactors;
    private String competitors;
    private String differentiatingFactors;
    private String businessModel;
    private String growthPotential;
    private String productVideoUrl;
    private String productWebsite;
    private Boolean hasOtherProducts;
}