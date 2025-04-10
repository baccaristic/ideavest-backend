package com.ideavest.server.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class AdditionalInfo {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String whyApply;
    private Boolean wantOtherBenefits;
    private Boolean certifyInformation;
    private Boolean acceptConditions;
    private Boolean authorizeSharing;
}