package com.david.wowStockCalculator.services.impl;

import com.david.wowStockCalculator.domain.entities.Resource;
import com.david.wowStockCalculator.domain.entities.Sale;
import com.david.wowStockCalculator.domain.entities.StockMapping;
import com.david.wowStockCalculator.repositories.ResourceRepository;
import com.david.wowStockCalculator.repositories.SaleRepository;
import com.david.wowStockCalculator.services.SaleService;
import com.david.wowStockCalculator.services.StockMappingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class SaleServiceImpl implements SaleService {

    private StockMappingService stockMappingService;
    private SaleRepository saleRepository;
    private ResourceRepository resourceRepository;
    private DateServiceImpl dateService;

    @Override
    public Sale createSale(Long resourceId, Sale sale) {
        return createSale(resourceId, sale, null);
    }

    @Override
    public Sale createSale(Long resourceId, Sale sale, List<Long> stockMappingIds) {
        Resource resource = resourceRepository.findById(resourceId).orElseThrow();
        resourceRepository.save(resource);
        try {
            if(stockMappingIds == null || stockMappingIds.isEmpty()) {
                stockMappingService.save(
                    resourceId, sale.getAmount().longValue(),
                    sale.getCost(), sale.getIsSold());
            } else {
                StockMapping stock = stockMappingService.getStockMapping(resourceId,
                        sale.getAmount().longValue(), sale.getCost());
                stockMappingService.merge(
                        stock,
                        stockMappingService.findByIdIn(stockMappingIds));
            }
        } catch (StockMappingServiceImpl.ZeroAmountException e) {
            // do nothing
        }

        sale.setResource(resource);
        sale.setDate(dateService.getNow());

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

        try {
            stockMappingService.save(
                    sale.getResource().getId(), sale.getAmount().longValue(),
                    sale.getCost(), !sale.getIsSold());
        } catch (StockMappingServiceImpl.ZeroAmountException e) {
            // do nothing
        }

        saleRepository.delete(sale);
    }

    @Override
    public Iterable<Sale> findAllByResourceId(Long resourceId) {
        return saleRepository.findAllByResourceId(resourceId);
    }

    @Override
    public Page<Sale> findAllByResourceId(Long resourceId, Pageable pageable) {
        return saleRepository.findAllByResourceId(resourceId, pageable);
    }
}
