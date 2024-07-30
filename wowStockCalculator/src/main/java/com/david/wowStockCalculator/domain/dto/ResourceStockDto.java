package com.david.wowStockCalculator.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResourceStockDto {

    private Long id;

    private String name;

    private String quality;

    private Integer amount;

    private Long value;
}
