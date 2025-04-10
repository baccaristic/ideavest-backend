package com.ideavest.server.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarketEntryDTO {
    private String target;
    private String country;
    private String year;
    private String marketType;
}
