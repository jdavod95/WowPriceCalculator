package com.david.wowStockCalculator.domain.dto;

import com.david.wowStockCalculator.domain.entities.Quality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResourceDto {

    private Long id;

    private String name;

    private Integer onStock;

    private String quality;
}
