package com.david.wowStockCalculator.controllers;

import com.david.wowStockCalculator.domain.dto.SaleDto;
import com.david.wowStockCalculator.domain.dto.SaleResponseDto;
import com.david.wowStockCalculator.domain.entities.Sale;
import com.david.wowStockCalculator.mappers.Mapper;
import com.david.wowStockCalculator.services.ResourceService;
import com.david.wowStockCalculator.services.SaleService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@Log
@AllArgsConstructor
public class SaleController {

    private Mapper<Sale, SaleResponseDto> saleResponseMapper;
    private Mapper<Sale, SaleDto> saleMapper;
    private SaleService saleService;
    private ResourceService resourceService;

    @GetMapping(path = "/salesPaged")
    public Page<SaleResponseDto> listSalesPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size){
        Page<Sale> sales = saleService.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
        return sales.map(saleResponseMapper::mapTo);
    }

    @GetMapping(path = "/salesPaged/{resource_id}")
    public Page<SaleResponseDto> listSalesPageByResourceId(
            @PathVariable("resource_id") Long resourceId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size){
        Page<Sale> sales = saleService.findAllByResourceId(resourceId, PageRequest.of(page, size, Sort.by("id").descending()));
        return sales.map(saleResponseMapper::mapTo);
    }

    @GetMapping(path = "/sales")
    public List<SaleResponseDto> listSales(){
        return saleService.findAll().stream()
                .map(saleResponseMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/sales/{resource_id}")
    public ResponseEntity<List<SaleResponseDto>> listSalesByResourceId(
            @PathVariable("resource_id") Long resourceId
    ){
        List<SaleResponseDto> sales = StreamSupport.stream(saleService.findAllByResourceId(resourceId).spliterator(), false)
                .map(saleResponseMapper::mapTo)
                .collect(Collectors.toList());

        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @PostMapping(path = "/sales/{resource_id}")
    public ResponseEntity<SaleDto> createSale(
            @PathVariable("resource_id") Long resourceId,
            @RequestBody final SaleDto saleDto){
        if(!resourceService.isExists(resourceId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Sale savedSale = saleService.createSale(resourceId, saleMapper.mapFrom(saleDto), saleDto.getStockMappingIds());
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
