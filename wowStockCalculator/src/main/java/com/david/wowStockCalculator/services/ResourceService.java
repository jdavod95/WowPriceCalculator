package com.david.wowStockCalculator.services;

import com.david.wowStockCalculator.domain.entities.Resource;

import java.util.List;
import java.util.Optional;

public interface ResourceService {

    Resource createResource(Resource resource);

    List<Resource> findAll();

    Optional<Resource> findById(Long resourceId);
}
