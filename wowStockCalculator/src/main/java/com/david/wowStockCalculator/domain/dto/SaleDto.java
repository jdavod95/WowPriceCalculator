package com.david.wowStockCalculator.domain.dto;

import com.david.wowStockCalculator.domain.entities.StockMapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDto {

    private Long id;

    private ResourceDto resource;

    private String date;

    private Integer amount;

    private Long cost;

    private Boolean isSold;

    private List<Long> stockMappingIds;
}
