package com.ideavest.server.mappers;


import com.ideavest.server.dtos.ProgressDataDTO;
import com.ideavest.server.models.ProgressData;

public class ProgressDataMapper {
    public static ProgressData fromDto(ProgressDataDTO dto) {
        if (dto == null) return null;

        ProgressData entity = new ProgressData();
        entity.setProjectStage(dto.getProjectStage());
        entity.setCurrentProgress(dto.getCurrentProgress());
        entity.setJoinedIncubator(dto.getJoinedIncubator());
        entity.setWonEntrepreneurshipAward(dto.getWonEntrepreneurshipAward());
        entity.setFiledPatents(dto.getFiledPatents());

        return entity;
    }
}