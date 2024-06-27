package com.david.wowStockCalculator.controllers;

import com.david.wowStockCalculator.domain.entities.Resource;
import com.david.wowStockCalculator.domain.dto.ResourceDto;
import com.david.wowStockCalculator.mappers.Mapper;
import com.david.wowStockCalculator.services.ResourceService;
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
public class ResourceController {

    private ResourceService resourceService;
    private Mapper<Resource, ResourceDto> resourceMapper;

    public ResourceController(Mapper<Resource, ResourceDto> resourceMapper, ResourceService resourceService) {
        this.resourceMapper = resourceMapper;
        this.resourceService = resourceService;
    }

    @GetMapping(path = "/resources")
    public List<ResourceDto> listResources(){
        List<Resource> resources = resourceService.findAll();
        return resources.stream()
                .map(resourceMapper::mapTo)
                .collect(Collectors.toList());
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

    @PostMapping(path = "/resources")
    public ResponseEntity<ResourceDto> createResource(@RequestBody final ResourceDto resource){
        Resource resourceEntity = resourceMapper.mapFrom(resource);
        Resource savedResource = resourceService.createResource(resourceEntity);

        return new ResponseEntity<>(resourceMapper.mapTo(savedResource), HttpStatus.CREATED);
    }


}
