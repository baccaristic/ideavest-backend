package com.ideavest.server.mappers;

import com.ideavest.server.dtos.TeamDataDTO;
import com.ideavest.server.models.TeamData;

public class TeamDataMapper {
    public static TeamData fromDto(TeamDataDTO dto) {
        if (dto == null) return null;

        TeamData entity = new TeamData();
        entity.setNumberOfCofounders(dto.getNumberOfCofounders());
        entity.setTeamDescription(dto.getTeamDescription());
        entity.setTeamCapable(dto.getTeamCapable());
        entity.setTimeWorkingOnProject(dto.getTimeWorkingOnProject());
        entity.setWorkedTogetherBefore(dto.getWorkedTogetherBefore());
        entity.setLaunchedStartupBefore(dto.getLaunchedStartupBefore());

        return entity;
    }
}