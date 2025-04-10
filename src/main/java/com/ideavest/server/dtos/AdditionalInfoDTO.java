package com.ideavest.server.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalInfoDTO {
    private String whyApply;
    private Boolean wantOtherBenefits;
    private Boolean certifyInformation;
    private Boolean acceptConditions;
    private Boolean authorizeSharing;
}
