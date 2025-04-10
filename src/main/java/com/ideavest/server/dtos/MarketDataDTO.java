package com.ideavest.server.dtos;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarketDataDTO {
    private String targetMarket;
    private List<MarketEntryDTO> currentMarkets;
    private List<MarketEntryDTO> futureMarkets;
    private String marketSize;
    private String targetMarketShare;
    private String growthStrategy;
    private String currentUsers;
    private String projectedUsers;
}
