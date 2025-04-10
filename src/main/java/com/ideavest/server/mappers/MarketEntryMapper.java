package com.ideavest.server.mappers;


import com.ideavest.server.dtos.MarketEntryDTO;
import com.ideavest.server.models.MarketEntry;

public class MarketEntryMapper {
    public static MarketEntry fromDto(MarketEntryDTO dto) {
        if (dto == null) return null;

        MarketEntry entity = new MarketEntry();
        entity.setTarget(dto.getTarget());
        entity.setCountry(dto.getCountry());
        entity.setYear(dto.getYear());
        entity.setMarketType(dto.getMarketType());

        return entity;
    }
}