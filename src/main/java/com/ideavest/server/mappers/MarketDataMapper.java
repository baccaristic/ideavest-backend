package com.ideavest.server.mappers;

import com.ideavest.server.dtos.MarketDataDTO;
import com.ideavest.server.models.MarketData;

import java.util.stream.Collectors;

public class MarketDataMapper {
    public static MarketData fromDto(MarketDataDTO dto) {
        if (dto == null) return null;

        MarketData entity = new MarketData();
        entity.setTargetMarket(dto.getTargetMarket());
        entity.setMarketSize(dto.getMarketSize());
        entity.setTargetMarketShare(dto.getTargetMarketShare());
        entity.setGrowthStrategy(dto.getGrowthStrategy());
        entity.setCurrentUsers(dto.getCurrentUsers());
        entity.setProjectedUsers(dto.getProjectedUsers());

        if (dto.getCurrentMarkets() != null) {
            entity.setCurrentMarkets(dto.getCurrentMarkets().stream()
                .map(MarketEntryMapper::fromDto).collect(Collectors.toList()));
        }
        if (dto.getFutureMarkets() != null) {
            entity.setFutureMarkets(dto.getFutureMarkets().stream()
                .map(MarketEntryMapper::fromDto).collect(Collectors.toList()));
        }

        return entity;
    }
}