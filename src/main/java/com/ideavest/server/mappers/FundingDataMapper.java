package com.ideavest.server.mappers;


import com.ideavest.server.dtos.FundingDataDTO;
import com.ideavest.server.models.FundingData;

public class FundingDataMapper {
    public static FundingData fromDto(FundingDataDTO dto) {
        if (dto == null) return null;

        FundingData entity = new FundingData();
        entity.setFundraisingGoal(dto.getFundraisingGoal());
        entity.setRevenueProjection(dto.getRevenueProjection());

        return entity;
    }
}