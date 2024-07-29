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
public class RecipeResponseDto {

    private Long id;

    private String name;

    private List<ReagentResponseDto> requiredReagents;

    private List<ReagentResponseDto> resultReagents;

    private Integer difficulty;

    private List<CraftingStat> craftingStats;
}
