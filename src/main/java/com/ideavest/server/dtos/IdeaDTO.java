package com.ideavest.server.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IdeaDTO {
    private String title;
    private String description;
    private String category;
    private Double estimatedBudget;
    private Double estimatedPrice;

    private IdeaAdditionalDataDTO additionalData;
    private List<String> tags;
}