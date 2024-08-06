package com.david.wowStockCalculator;

import com.david.wowStockCalculator.domain.dto.ResourceDto;
import com.david.wowStockCalculator.domain.dto.SaleResponseDto;
import com.david.wowStockCalculator.domain.entities.Resource;
import com.david.wowStockCalculator.domain.entities.Sale;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class TestDataUtil {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    private TestDataUtil(){}

    public static Resource createTestResourceA() {
        return Resource.builder()
                .id(1L)
                .name("Dracothyst")
                .build();
    }

    public static Resource createTestResourceB() {
        return Resource.builder()
                .id(2L)
                .name("Zaralek Glowspore")
                .build();
    }

    public static Resource createTestResourceC() {
        return Resource.builder()
                .id(3L)
                .name("Sliken Gemdust")
                .build();
    }

    public static Sale createTestSaleA() {
        return createTestSaleA(null);
    }

    public static Sale createTestSaleA(final Resource resource) {
        return Sale.builder()
                .id(1L)
                .date(getNow())
                .resource(resource)
                .amount(1)
                .cost(1L)
                .build();
    }


    public static Sale createTestSaleB(final Resource resource) {
        return Sale.builder()
                .id(2L)
                .date(getNow())
                .resource(resource)
                .amount(300)
                .cost(2L)
                .build();
    }

    public static SaleResponseDto createTestSaleDtoA(final ResourceDto resourceDto) {
        return SaleResponseDto.builder()
                .id(1L)
                .date(getNow())
                .resource(resourceDto)
                .amount(1)
                .cost(1L)
                .build();
    }

    public static ResourceDto createTestResourceDtoA() {
        return ResourceDto.builder()
                .id(1L)
                .name("Dracothyst")
                .build();
    }

    public static String getNow() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public static String getYesterday() {
        return LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
