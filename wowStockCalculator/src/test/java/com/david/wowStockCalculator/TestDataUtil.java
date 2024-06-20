package com.david.wowStockCalculator;

import com.david.wowStockCalculator.domain.Resource;
import com.david.wowStockCalculator.domain.Sale;
import org.assertj.core.util.DateUtil;

import java.util.Date;

public final class TestDataUtil {

    private TestDataUtil(){}

    public static Resource createTestResourceA() {
        return Resource.builder()
                .id(1L)
                .name("Dracothyst")
                .onStock(0)
                .build();
    }

    public static Resource createTestResourceB() {
        return Resource.builder()
                .id(2L)
                .name("Zaralek Glowspore")
                .onStock(0)
                .build();
    }

    public static Sale createTestSaleA() {
        return Sale.builder()
                .id(1L)
                .date(DateUtil.truncateTime(DateUtil.now()))
                .resourceId(1L)
                .amount(1)
                .cost(1)
                .build();
    }

    public static Sale createTestSaleB() {
        return Sale.builder()
                .id(2L)
                .date(DateUtil.truncateTime(DateUtil.now()))
                .resourceId(2L)
                .amount(300)
                .cost(2)
                .build();
    }
}
