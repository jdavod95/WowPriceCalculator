package com.david.wowStockCalculator.services.impl;

import com.david.wowStockCalculator.domain.entities.Resource;
import com.david.wowStockCalculator.repositories.ResourceRepository;
import com.david.wowStockCalculator.services.ResourceService;
import org.springframework.stereotype.Service;

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


}
