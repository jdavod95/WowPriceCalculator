package com.david.wowStockCalculator.controllers;

import com.david.wowStockCalculator.domain.dto.StockMappingDto;
import com.david.wowStockCalculator.domain.entities.StockMapping;
import com.david.wowStockCalculator.mappers.Mapper;
import com.david.wowStockCalculator.services.StockMappingService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@Log
@AllArgsConstructor
public class StockMappingController {

    private StockMappingService stockMappingService;
    private Mapper<StockMapping, StockMappingDto> stockMappingMapper;

    @GetMapping(path = "/stockMapping/{resource_id}")
    public Iterable<StockMappingDto> getStockMappingList(
            @PathVariable("resource_id") Long resourceId
    ){
       return StreamSupport.stream(stockMappingService.findAllByResourceId(resourceId).spliterator(), false)
                .map(stockMappingMapper::mapTo)
                .collect(Collectors.toList());
    }

}
