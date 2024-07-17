package com.david.wowStockCalculator.services;

import com.david.wowStockCalculator.domain.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SaleService {

    Sale createSale(Long resourceId, Sale sale);

    List<Sale> findAll();

    Page<Sale> findAll(Pageable pageable);

    Optional<Sale> findById(Long saleId);

    boolean isExists(Long id);

    void delete(Long id);

    Iterable<Sale> findAllByResourceId(Long resourceId);

    Page<Sale> findAllByResourceId(Long resourceId, Pageable pageable);
}
