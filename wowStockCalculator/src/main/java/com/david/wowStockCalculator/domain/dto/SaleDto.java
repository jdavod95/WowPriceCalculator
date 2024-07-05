package com.david.wowStockCalculator.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDto {

    private Long id;

    private ResourceDto resource;

    private String date;

    private Integer amount;

    private Integer cost;

    private Boolean isSold;
}
