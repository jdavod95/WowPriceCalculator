package com.david.wowStockCalculator.controllers;

import com.david.wowStockCalculator.domain.entities.Sale;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class SaleController {
/*
    @GetMapping(path = "/sales")
    public Sale retrieveSale(){
        return Sale.builder()
                .id(1L)
                .date(getNow())
                .resource(resource)
                .amount(1)
                .cost(1)
                .build();
    }
*/
    @PostMapping(path = "/sales")
    public Sale createSale(@RequestBody final Sale sale){
        log.info("Sale created " + sale.toString());
        return sale;
    }
}
