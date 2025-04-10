package com.ideavest.server.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IdeaAdditionalDataDTO {
    private String sector;
    private String technology;
    private String region;
    private String website;
    private InnovationDataDTO innovation;
    private MarketDataDTO market;
    private ProgressDataDTO progress;
    private TeamDataDTO team;
    private PresentationDataDTO presentation;
    private FundingDataDTO funding;
    private AdditionalInfoDTO additional;
}
