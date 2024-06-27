package com.david.wowStockCalculator.services.impl;

import com.david.wowStockCalculator.domain.entities.Resource;
import com.david.wowStockCalculator.domain.entities.Sale;
import com.david.wowStockCalculator.repositories.ResourceRepository;
import com.david.wowStockCalculator.repositories.SaleRepository;
import com.david.wowStockCalculator.services.SaleService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {

    private SaleRepository saleRepository;
    private ResourceRepository resourceRepository;

    public SaleServiceImpl(SaleRepository saleRepository, ResourceRepository resourceRepository) {
        this.saleRepository = saleRepository;
        this.resourceRepository = resourceRepository;
    }

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
}
