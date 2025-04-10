
package com.ideavest.server.dtos;

import lombok.*;

import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class EstimationDTO {
    private UUID ideaId;
    private Double price;
    private String notes;
}
