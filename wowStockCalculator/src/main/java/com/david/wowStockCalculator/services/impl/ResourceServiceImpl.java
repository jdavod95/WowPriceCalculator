package com.david.wowStockCalculator.services.impl;

import com.david.wowStockCalculator.domain.entities.Resource;
import com.david.wowStockCalculator.repositories.ResourceRepository;
import com.david.wowStockCalculator.services.ResourceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ResourceServiceImpl implements ResourceService {

    private ResourceRepository resourceRepository;

    public ResourceServiceImpl(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Override
    public Resource createResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    @Override
    public List<Resource> findAll() {
        return StreamSupport.stream(resourceRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Resource> findById(Long resourceId) {
        return resourceRepository.findById(resourceId);
    }


}
