package com.david.wowStockCalculator.controllers;

import com.david.wowStockCalculator.domain.dto.SaleDto;
import com.david.wowStockCalculator.domain.entities.Sale;
import com.david.wowStockCalculator.mappers.Mapper;
import com.david.wowStockCalculator.services.SaleService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class SaleController {

    private Mapper<Sale, SaleDto> saleMapper;
    private SaleService saleService;

    public SaleController(SaleService saleService, Mapper<Sale, SaleDto> saleMapper) {
        this.saleService = saleService;
        this.saleMapper = saleMapper;
    }

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

    @PostMapping(path = "/sales/{resource_id}")
    public ResponseEntity<SaleDto> createSale(
            @PathVariable("resource_id") Long resource_id,
            @RequestBody final SaleDto saleDto){
        Sale savedSale = saleService.createSale(resource_id, saleMapper.mapFrom(saleDto));
        return new ResponseEntity<>(saleMapper.mapTo(savedSale), HttpStatus.CREATED);
    }
}
