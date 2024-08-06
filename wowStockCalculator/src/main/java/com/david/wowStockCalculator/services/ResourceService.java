package com.david.wowStockCalculator.services;

import com.david.wowStockCalculator.domain.entities.Quality;
import com.david.wowStockCalculator.domain.entities.Resource;
import com.david.wowStockCalculator.domain.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    Page<Resource> findAll(Pageable pageable);

    List<Resource> findByIdIn(List<Long> resourceIds);
}
