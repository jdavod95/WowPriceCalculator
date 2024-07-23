package com.david.wowStockCalculator.services.impl;

import com.david.wowStockCalculator.domain.entities.Reagent;
import com.david.wowStockCalculator.domain.entities.Recipe;
import com.david.wowStockCalculator.domain.entities.Resource;
import com.david.wowStockCalculator.repositories.ReagentRepository;
import com.david.wowStockCalculator.services.ReagentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class ReagentServiceImpl implements ReagentService {

    private ReagentRepository reagentRepository;

    @Override
    public Reagent createReagent(Reagent reagent) {
        return reagentRepository.save(reagent);
    }

    @Override
    public List<Reagent> findAll() {
        return StreamSupport.stream(reagentRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Reagent> findById(Long id) {
        return reagentRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        Reagent reagent = reagentRepository.findById(id).get();
        reagentRepository.delete(reagent);
    }

    @Override
    public boolean isExists(Long id) {
        return reagentRepository.existsById(id);
    }
}
