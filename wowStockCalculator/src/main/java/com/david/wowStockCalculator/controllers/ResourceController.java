package com.david.wowStockCalculator.controllers;

import com.david.wowStockCalculator.domain.dto.ResourceStockDto;
import com.david.wowStockCalculator.domain.dto.StockMappingDto;
import com.david.wowStockCalculator.domain.entities.Quality;
import com.david.wowStockCalculator.domain.entities.Resource;
import com.david.wowStockCalculator.domain.dto.ResourceDto;
import com.david.wowStockCalculator.domain.entities.StockMapping;
import com.david.wowStockCalculator.mappers.Mapper;
import com.david.wowStockCalculator.services.ResourceService;
import com.david.wowStockCalculator.services.StockMappingService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@Log
@AllArgsConstructor
public class ResourceController {

    private ResourceService resourceService;
    private StockMappingService stockMappingService;
    private Mapper<Resource, ResourceDto> resourceMapper;
    private Mapper<Resource, ResourceStockDto> resourceStockMapper;

    @GetMapping(path = "/resources")
    public List<ResourceDto> listResources(){
        List<Resource> resources = resourceService.findAll();
        return resources.stream()
                .map(resourceMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/resourcesPaged")
    public Page<ResourceStockDto> listResourcesPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size){
        Page<Resource> resources = resourceService.findAll(PageRequest.of(page, size, Sort.by("name").ascending()));
        Page<ResourceStockDto> resourceStockDtos = resources.map(resourceStockMapper::mapTo);
        resourceStockDtos.forEach(resourceStockDto -> {
            Iterable<StockMapping> stocks = stockMappingService.findAllByResourceId(resourceStockDto.getId());

            resourceStockDto.setAmount(
                    StreamSupport.stream(stocks.spliterator(), false)
                            .collect(Collectors.summingInt(StockMapping::getAmount))
            );

            long top = 0;
            long bottom = 0;

            for (StockMapping stockMapping : stocks) {
                top += (stockMapping.getValue() * stockMapping.getAmount());
                bottom += stockMapping.getAmount();
            }

            resourceStockDto.setValue(top/ bottom);
        });

        return resourceStockDtos;
    }

    @GetMapping(path = "/resources/{resource_id}")
    public ResponseEntity<ResourceDto> getResource(
            @PathVariable("resource_id") Long resourceId
    ){
        Optional<Resource> foundResource = resourceService.findById(resourceId);
        return foundResource.map(
                resource -> new ResponseEntity<>(resourceMapper.mapTo(resource), HttpStatus.OK)
        ).orElse(
            new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PutMapping(path = "/resources")
    public ResponseEntity<ResourceDto> createResource(@RequestBody final ResourceDto resource){
        Resource resourceEntity = resourceMapper.mapFrom(resource);
        Optional<Resource> existingResource = resourceService.find(
                resourceEntity.getName(),
                Optional.ofNullable(resourceEntity.getQuality()).orElse(Quality.NONE));

        if(existingResource.isPresent()) {
            return new ResponseEntity<>(resourceMapper.mapTo(existingResource.get()), HttpStatus.OK);
        }

        Resource savedResource = resourceService.createResource(resourceEntity);

        return new ResponseEntity<>(resourceMapper.mapTo(savedResource), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/resources/{id}")
    public ResponseEntity<ResourceDto> partialUpdate(
            @PathVariable("id") Long id,
            @RequestBody ResourceDto resourceDto
    ) {
        if(!resourceService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Resource resourceEntity = resourceMapper.mapFrom(resourceDto);
        Resource updatedResource = resourceService.partialUpdate(id, resourceEntity);
        return new ResponseEntity<>(resourceMapper.mapTo(updatedResource), HttpStatus.OK);
    }

    @DeleteMapping(path = "/resources/{id}")
    public ResponseEntity delete(
            @PathVariable("id") Long id
    ) {
        if(!resourceService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        resourceService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
