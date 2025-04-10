package com.ideavest.server.mappers;


import com.ideavest.server.dtos.IdeaAdditionalDataDTO;
import com.ideavest.server.models.IdeaAdditionalData;

public class IdeaAdditionalDataMapper {

    public static IdeaAdditionalData fromDto(IdeaAdditionalDataDTO dto) {
        if (dto == null) return null;

        IdeaAdditionalData entity = new IdeaAdditionalData();
        entity.setSector(dto.getSector());
        entity.setTechnology(dto.getTechnology());
        entity.setRegion(dto.getRegion());
        entity.setWebsite(dto.getWebsite());

        entity.setInnovation(InnovationDataMapper.fromDto(dto.getInnovation()));
        entity.setMarket(MarketDataMapper.fromDto(dto.getMarket()));
        entity.setProgress(ProgressDataMapper.fromDto(dto.getProgress()));
        entity.setTeam(TeamDataMapper.fromDto(dto.getTeam()));
        entity.setPresentation(PresentationDataMapper.fromDto(dto.getPresentation()));
        entity.setFunding(FundingDataMapper.fromDto(dto.getFunding()));
        entity.setAdditional(AdditionalInfoMapper.fromDto(dto.getAdditional()));

        return entity;
    }
}