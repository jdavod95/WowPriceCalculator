package com.david.wowStockCalculator.controllers;

import com.david.wowStockCalculator.domain.dto.ResourceDto;
import com.david.wowStockCalculator.domain.dto.SaleDto;
import com.david.wowStockCalculator.domain.entities.Resource;
import com.david.wowStockCalculator.domain.entities.Sale;
import com.david.wowStockCalculator.mappers.Mapper;
import com.david.wowStockCalculator.services.SaleService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Log
public class SaleController {

    private Mapper<Sale, SaleDto> saleMapper;
    private SaleService saleService;

    public SaleController(SaleService saleService, Mapper<Sale, SaleDto> saleMapper) {
        this.saleService = saleService;
        this.saleMapper = saleMapper;
    }

    @GetMapping(path = "/sales")
    public List<SaleDto> listSales(){
        List<Sale> sales = saleService.findAll();
        return sales.stream()
                .map(saleMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/sales/{sale_id}")
    public ResponseEntity<SaleDto> getSale(
            @PathVariable("sale_id") Long saleId
    ){
        Optional<Sale> foundSale = saleService.findById(saleId);

        return foundSale.map(
                sale -> new ResponseEntity<>(saleMapper.mapTo(sale), HttpStatus.OK)
        ).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping(path = "/sales/{resource_id}")
    public ResponseEntity<SaleDto> createSale(
            @PathVariable("resource_id") Long resourceId,
            @RequestBody final SaleDto saleDto){
        Sale savedSale = saleService.createSale(resourceId, saleMapper.mapFrom(saleDto));
        return new ResponseEntity<>(saleMapper.mapTo(savedSale), HttpStatus.CREATED);
    }
}
