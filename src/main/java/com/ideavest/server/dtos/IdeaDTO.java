package com.ideavest.server.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class IdeaDTO {
    private String title;
    private String description;
    private String category;
    private Double estimatedBudget;
}
