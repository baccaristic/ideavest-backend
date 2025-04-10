package com.ideavest.server.mappers;


import com.ideavest.server.dtos.InnovationDataDTO;
import com.ideavest.server.models.InnovationData;

public class InnovationDataMapper {
    public static InnovationData fromDto(InnovationDataDTO dto) {
        if (dto == null) return null;

        InnovationData entity = new InnovationData();
        entity.setProjectDescription(dto.getProjectDescription());
        entity.setProblemStatement(dto.getProblemStatement());
        entity.setSolution(dto.getSolution());
        entity.setInnovationFactors(dto.getInnovationFactors());
        entity.setCompetitors(dto.getCompetitors());
        entity.setDifferentiatingFactors(dto.getDifferentiatingFactors());
        entity.setBusinessModel(dto.getBusinessModel());
        entity.setGrowthPotential(dto.getGrowthPotential());
        entity.setProductVideoUrl(dto.getProductVideoUrl());
        entity.setProductWebsite(dto.getProductWebsite());
        entity.setHasOtherProducts(dto.getHasOtherProducts());

        return entity;
    }
}