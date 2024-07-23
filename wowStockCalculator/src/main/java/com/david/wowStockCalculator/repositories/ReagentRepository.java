package com.david.wowStockCalculator.repositories;

import com.david.wowStockCalculator.domain.entities.Reagent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReagentRepository extends CrudRepository<Reagent, Long>,
        PagingAndSortingRepository<Reagent, Long> {

}
