package com.ideavest.server.mappers;


import com.ideavest.server.dtos.PresentationDataDTO;
import com.ideavest.server.models.PresentationData;

public class PresentationDataMapper {
    public static PresentationData fromDto(PresentationDataDTO dto) {
        if (dto == null) return null;

        PresentationData entity = new PresentationData();
        entity.setPitchDeckUrl(dto.getPitchDeckUrl());
        entity.setPitchVideoUrl(dto.getPitchVideoUrl());

        return entity;
    }
}