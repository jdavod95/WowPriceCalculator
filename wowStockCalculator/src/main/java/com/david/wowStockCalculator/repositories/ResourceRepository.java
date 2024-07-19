package com.david.wowStockCalculator.repositories;

import com.david.wowStockCalculator.domain.entities.Quality;
import com.david.wowStockCalculator.domain.entities.Resource;
import com.david.wowStockCalculator.domain.entities.Sale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResourceRepository extends CrudRepository<Resource, Long>,
        PagingAndSortingRepository<Resource, Long> {

    Iterable<Resource> onStockGreaterThan(int onStock);

    Optional<Resource> findByNameAndQuality(String name, Quality quality);
}
