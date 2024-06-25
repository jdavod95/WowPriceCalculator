package com.david.wowStockCalculator.repositories;

import com.david.wowStockCalculator.domain.Resource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends CrudRepository<Resource, Long> {
}
