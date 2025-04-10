package com.ideavest.server.mappers;


import com.ideavest.server.dtos.AdditionalInfoDTO;
import com.ideavest.server.models.AdditionalInfo;

public class AdditionalInfoMapper {
    public static AdditionalInfo fromDto(AdditionalInfoDTO dto) {
        if (dto == null) return null;

        AdditionalInfo entity = new AdditionalInfo();
        entity.setWhyApply(dto.getWhyApply());
        entity.setWantOtherBenefits(dto.getWantOtherBenefits());
        entity.setCertifyInformation(dto.getCertifyInformation());
        entity.setAcceptConditions(dto.getAcceptConditions());
        entity.setAuthorizeSharing(dto.getAuthorizeSharing());

        return entity;
    }
}