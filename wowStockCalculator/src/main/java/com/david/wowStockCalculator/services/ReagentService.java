package com.david.wowStockCalculator.services;

import com.david.wowStockCalculator.domain.entities.Reagent;

import java.util.List;
import java.util.Optional;

public interface ReagentService {

    Reagent createReagent(Reagent recipe);

    List<Reagent> findAll();

    Optional<Reagent> findById(Long id);

    void delete(Long id);

    boolean isExists(Long id);
}
