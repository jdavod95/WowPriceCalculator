package com.david.wowStockCalculator;

import com.david.wowStockCalculator.domain.Resource;
import com.david.wowStockCalculator.domain.Sale;
import org.assertj.core.util.DateUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public final class TestDataUtil {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    private TestDataUtil(){}

    public static Resource createTestResourceA() {
        return Resource.builder()
                .id(1L)
                .name("Dracothyst")
                .onStock(1)
                .build();
    }

    public static Resource createTestResourceB() {
        return Resource.builder()
                .id(2L)
                .name("Zaralek Glowspore")
                .onStock(300)
                .build();
    }

    public static Resource createTestResourceC() {
        return Resource.builder()
                .id(3L)
                .name("Sliken Gemdust")
                .onStock(0)
                .build();
    }

    public static Sale createTestSaleA(final Resource resource) {
        return Sale.builder()
                .id(1L)
                .date(getNow())
                .resource(resource)
                .amount(1)
                .cost(1)
                .build();
    }

    public static Sale createTestSaleB(final Resource resource) {
        return Sale.builder()
                .id(2L)
                .date(getNow())
                .resource(resource)
                .amount(300)
                .cost(2)
                .build();
    }

    private static String getNow() {
        return LocalDate.now().format(DateTimeFormatter.ISO_DATE);
    }

}
