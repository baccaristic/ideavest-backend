
package com.ideavest.server.dtos;

import lombok.*;

import java.util.UUID;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class IdeaUpdateAssignedToDTO {
    private UUID ideaId;
    private UUID userId;
}
