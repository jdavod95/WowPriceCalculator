package com.david.wowStockCalculator.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceStockDto extends ResourceDto {

    private Long amount;

    private Long value;
}
