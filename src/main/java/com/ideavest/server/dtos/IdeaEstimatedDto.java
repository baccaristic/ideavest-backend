package com.ideavest.server.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class IdeaEstimatedDto {
    private UUID id;
    private String title;
    private String description;
    private boolean canView;
}
