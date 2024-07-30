package com.david.wowStockCalculator.services.impl;

import com.david.wowStockCalculator.domain.entities.StockMapping;
import com.david.wowStockCalculator.repositories.ResourceRepository;
import com.david.wowStockCalculator.repositories.StockMappingRepository;
import com.david.wowStockCalculator.services.StockMappingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StockMappingServiceImpl implements StockMappingService {

    private StockMappingRepository stockMappingRepository;
    private ResourceRepository resourceRepository;

    @Override
    public StockMapping save(StockMapping stockMapping) {
        return stockMappingRepository.save(stockMapping);
    }

    @Override
    public StockMapping save(Long resourceId, Integer amount, Long value, Boolean isSold)
            throws ZeroAmountException {
        amount = amount * (isSold ? -1 : 1);

        StockMapping stockMapping;
        Optional<StockMapping> existingMapping =
                stockMappingRepository.findByResourceIdAndValue(
                        resourceId,
                        Math.abs(value / amount));

        if(existingMapping.isPresent()) {
        System.out.println(existingMapping.get());
            stockMapping = existingMapping.get();
            Integer newAmount = amount + stockMapping.getAmount() ;

            if(newAmount == 0){
                delete(stockMapping.getId());
                throw new ZeroAmountException();
            }

            stockMapping.setAmount(newAmount);
        } else {
            stockMapping = StockMapping.builder()
                    .resource(resourceRepository.findById(resourceId).get())
                    .amount(amount)
                    .value(Math.abs(value / amount))
                    .build();
        }

        return stockMappingRepository.save(stockMapping);
    }

    @Override
    public void delete(Long id) {
        stockMappingRepository.delete(stockMappingRepository.findById(id).get());
    }

    @Override
    public Iterable<StockMapping> findAllByResourceId(Long resourceId) {
        return stockMappingRepository.findAllByResourceId(resourceId);
    }

    @Override
    public Optional<StockMapping> findByResourceIdAndValue(Long resourceId, Long value) {
        return stockMappingRepository.findByResourceIdAndValue(resourceId, value);
    }

    public class ZeroAmountException extends Throwable {
        public ZeroAmountException() {
            super("Stock amount is zero, deleting stock");
        }
    }
}
