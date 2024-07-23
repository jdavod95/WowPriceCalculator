package com.david.wowStockCalculator.domain.dto;

import com.david.wowStockCalculator.domain.entities.CraftingStat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeDto {

    private Long id;

    private String name;

    private List<ReagentDto> reagents;

    private Integer difficulty;

    private Integer resultAmount;

    private List<CraftingStat> craftingStats;
}
