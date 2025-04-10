package com.ideavest.server.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
public class MarketData {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String targetMarket;
    private String marketSize;
    private String targetMarketShare;
    private String growthStrategy;
    private String currentUsers;
    private String projectedUsers;

    @ElementCollection
    private List<MarketEntry> currentMarkets = new ArrayList<>();

    @ElementCollection
    private List<MarketEntry> futureMarkets = new ArrayList<>();
}