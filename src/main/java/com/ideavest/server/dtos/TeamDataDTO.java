package com.ideavest.server.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamDataDTO {
    private String numberOfCofounders;
    private String teamDescription;
    private Boolean teamCapable;
    private String timeWorkingOnProject;
    private Boolean workedTogetherBefore;
    private Boolean launchedStartupBefore;
}
