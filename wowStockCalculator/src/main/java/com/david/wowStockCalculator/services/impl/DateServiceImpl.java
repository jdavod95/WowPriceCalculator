package com.david.wowStockCalculator.services.impl;

import com.david.wowStockCalculator.services.DateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DateServiceImpl implements DateService {

    @Override
    public String getNow() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
