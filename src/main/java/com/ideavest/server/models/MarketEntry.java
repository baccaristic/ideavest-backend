package com.ideavest.server.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class MarketEntry {
    private String target;
    private String country;
    @Column(name = "\"year\"")
    private String year;
    private String marketType;
}