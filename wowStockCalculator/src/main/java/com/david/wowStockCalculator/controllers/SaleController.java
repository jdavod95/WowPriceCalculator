package com.david.wowStockCalculator.controllers;

import com.david.wowStockCalculator.domain.dto.SaleDto;
import com.david.wowStockCalculator.domain.entities.Sale;
import com.david.wowStockCalculator.mappers.Mapper;
import com.david.wowStockCalculator.services.ResourceService;
import com.david.wowStockCalculator.services.SaleService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@Log
@AllArgsConstructor
public class SaleController {

    private Mapper<Sale, SaleDto> saleMapper;
    private SaleService saleService;
    private ResourceService resourceService;

    @GetMapping(path = "/salesPaged")
    public Page<SaleDto> listSales(Pageable pageable){
        Page<Sale> sales = saleService.findAll(pageable);
        return sales.map(saleMapper::mapTo);
    }

    @GetMapping(path = "/sales")
    public List<SaleDto> listSales(){
        return saleService.findAll().stream()
                .map(saleMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/sales/{resource_id}")
    public List<SaleDto> listSalesByResourceId(
            @PathVariable("resource_id") Long resourceId
    ){
        return StreamSupport.stream(saleService.findAllByResourceId(resourceId).spliterator(), false)
                .map(saleMapper::mapTo)
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/sales/{resource_id}")
    public ResponseEntity<SaleDto> createSale(
            @PathVariable("resource_id") Long resourceId,
            @RequestBody final SaleDto saleDto){
        if(!resourceService.isExists(resourceId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Sale savedSale = saleService.createSale(resourceId, saleMapper.mapFrom(saleDto));
        return new ResponseEntity<>(saleMapper.mapTo(savedSale), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/sales/{id}")
    public ResponseEntity delete(
            @PathVariable("id") Long id
    ) {
        if(!saleService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        saleService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
