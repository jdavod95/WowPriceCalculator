package com.david.wowStockCalculator.services.impl;

import com.david.wowStockCalculator.domain.entities.StockMapping;
import com.david.wowStockCalculator.repositories.ResourceRepository;
import com.david.wowStockCalculator.repositories.StockMappingRepository;
import com.david.wowStockCalculator.services.StockMappingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StockMappingServiceImpl implements StockMappingService {

    private StockMappingRepository stockMappingRepository;
    private ResourceRepository resourceRepository;

    @Override
    public StockMapping save(StockMapping stockMapping) throws ZeroAmountException {
        if (stockMapping.getAmount() == 0) {
            delete(stockMapping.getId());
            throw new ZeroAmountException();
        }
        return stockMappingRepository.save(stockMapping);
    }

    @Override
    public StockMapping save(Long resourceId, Long amount, Long value, Boolean isSold)
            throws ZeroAmountException {
        amount *= (isSold ? -1 : 1);

        StockMapping stockMapping;
        Optional<StockMapping> existingMapping =
                stockMappingRepository.findByResourceIdAndValue(
                        resourceId,
                        Math.abs(value / amount));
        if(existingMapping.isPresent()) {
            stockMapping = existingMapping.get();
            Long newAmount = amount + stockMapping.getAmount() ;
            if(newAmount == 0){
                delete(stockMapping.getId());
                throw new ZeroAmountException();
            }

            stockMapping.setAmount(newAmount);
        } else {
            stockMapping = getStockMapping(resourceId, amount, value);
        }

        return stockMappingRepository.save(stockMapping);
    }

    @Override
    public StockMapping getStockMapping(Long resourceId, Long amount, Long value) {
        return StockMapping.builder()
                .resource(resourceRepository.findById(resourceId).get())
                .amount(amount)
                .value(Math.abs(value / amount))
                .build();
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

    @Override
    public List<StockMapping> findByIdIn(List<Long> stockMappingIds){
        return stockMappingRepository.findByIdIn(stockMappingIds);
    }

    @Override
    public StockMapping merge(StockMapping targetStock, List<StockMapping> sourceStock) throws ZeroAmountException {
        sourceStock.forEach(source ->{
            Long targetTotal = targetStock.getAmount() * targetStock.getValue();
            Long sourceTotal = source.getAmount() * source.getValue();
            Long newTotal = targetTotal + sourceTotal;
            Long newAmount = targetStock.getAmount() + source.getAmount();

            targetStock.setAmount(newAmount);
            targetStock.setValue(newTotal / newAmount);
            delete(source.getId());
        });

        return save(targetStock);
    }

    public class ZeroAmountException extends Throwable {
        public ZeroAmountException() {
            super("Stock amount is zero, deleting stock");
        }
    }
}
