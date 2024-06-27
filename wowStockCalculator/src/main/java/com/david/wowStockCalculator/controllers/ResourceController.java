package com.david.wowStockCalculator.controllers;

import com.david.wowStockCalculator.domain.entities.Resource;
import com.david.wowStockCalculator.domain.dto.ResourceDto;
import com.david.wowStockCalculator.mappers.Mapper;
import com.david.wowStockCalculator.services.ResourceService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class ResourceController {

    private ResourceService resourceService;
    private Mapper<Resource, ResourceDto> resourceMapper;

    public ResourceController(Mapper<Resource, ResourceDto> resourceMapper, ResourceService resourceService) {
        this.resourceMapper = resourceMapper;
        this.resourceService = resourceService;
    }
/*
    @GetMapping(path = "/resources")
    public Resource retrieveResource(){
        return Resource.builder()
                .id(1L)
                .name("Dracothyst")
                .onStock(1)
                .build();
    }
*/
    @PostMapping(path = "/resources")
    public ResponseEntity<ResourceDto> createResource(@RequestBody final ResourceDto resource){
        Resource resourceEntity = resourceMapper.mapFrom(resource);
        Resource savedResource = resourceService.createResource(resourceEntity);

        return new ResponseEntity<>(resourceMapper.mapTo(savedResource), HttpStatus.CREATED);
    }


}
