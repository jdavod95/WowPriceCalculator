package com.david.wowStockCalculator.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockMappingDto {
    private Long id;

    private ResourceDto resource;

    private Integer amount;

    private Long value;
}
