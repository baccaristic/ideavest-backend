package com.ideavest.server.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgressDataDTO {
    private String projectStage;
    private String currentProgress;
    private Boolean joinedIncubator;
    private Boolean wonEntrepreneurshipAward;
    private Boolean filedPatents;
}
