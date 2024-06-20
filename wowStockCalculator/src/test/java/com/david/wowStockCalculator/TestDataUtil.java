package com.david.wowStockCalculator;

import com.david.wowStockCalculator.domain.Resource;
import com.david.wowStockCalculator.domain.Sale;

import java.util.Date;

public final class TestDataUtil {

    private TestDataUtil(){}

    public static Resource createTestResource() {
        return Resource.builder()
                .id(1L)
                .name("Dracothyst")
                .onStock(0)
                .build();
    }

    public static Sale createTestSale(Date now) {
        return Sale.builder()
                .id(1L)
                .date(now)
                .resourceId(1L)
                .amount(1)
                .cost(1)
                .build();
    }
}
