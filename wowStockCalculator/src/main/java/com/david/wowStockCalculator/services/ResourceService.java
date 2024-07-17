package com.david.wowStockCalculator.services;

import com.david.wowStockCalculator.domain.entities.Quality;
import com.david.wowStockCalculator.domain.entities.Resource;

import java.util.List;
import java.util.Optional;

public interface ResourceService {

    Resource createResource(Resource resource);

    List<Resource> findAll();

    Optional<Resource> findById(Long resourceId);

    boolean isExists(Long id);

    Resource partialUpdate(Long id, Resource resourceEntity);

    void delete(Long id);

    Optional<Resource> find(String name, Quality quality);
}
