package com.david.wowStockCalculator.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sale {

    private Long id;

    private Date date;

    private Long resourceId;

    private Integer amount;

    private Integer cost;

}
