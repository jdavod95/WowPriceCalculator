package com.david.wowStockCalculator.services.impl;

import com.david.wowStockCalculator.domain.entities.Resource;
import com.david.wowStockCalculator.domain.entities.Sale;
import com.david.wowStockCalculator.repositories.ResourceRepository;
import com.david.wowStockCalculator.repositories.SaleRepository;
import com.david.wowStockCalculator.services.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class SaleServiceImpl implements SaleService {

    private SaleRepository saleRepository;
    private ResourceRepository resourceRepository;

    public static String getNow() {
        return LocalDate.now().format(DateTimeFormatter.ISO_DATE);
    }

    @Override
    public Sale createSale(Long resourceId, Sale sale) {
        Resource resource = resourceRepository.findById(resourceId).orElseThrow();

        resource.addToStock(sale.getAmount());
        resourceRepository.save(resource);

        sale.setResource(resource);
        sale.setDate(getNow());

        return saleRepository.save(sale);
    }

    @Override
    public List<Sale> findAll() {
        return StreamSupport.stream(saleRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Sale> findAll(Pageable pageable) {
        return saleRepository.findAll(pageable);
    }

    @Override
    public Optional<Sale> findById(Long saleId) {
        return saleRepository.findById(saleId);
    }

    @Override
    public boolean isExists(Long id) {
        return saleRepository.existsById(id);
    }

    @Override
    public void delete(Long id) {
        Sale sale = saleRepository.findById(id).get();

        Resource resource = sale.getResource();
        resource.addToStock(sale.getAmount() * -1);
        resourceRepository.save(resource);

        saleRepository.delete(sale);
    }

    @Override
    public Iterable<Sale> findAllByResourceId(Long resourceId) {
        return saleRepository.findAllByResourceId(resourceId);
    }
}
