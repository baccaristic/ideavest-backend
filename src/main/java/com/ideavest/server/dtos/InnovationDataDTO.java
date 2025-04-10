package com.ideavest.server.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InnovationDataDTO {
    private String projectDescription;
    private String problemStatement;
    private String solution;
    private String innovationFactors;
    private String competitors;
    private String differentiatingFactors;
    private String businessModel;
    private String growthPotential;
    private String productVideoUrl;
    private String productWebsite;
    private Boolean hasOtherProducts;
}
