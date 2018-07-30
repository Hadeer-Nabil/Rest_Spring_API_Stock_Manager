package com.hadeer.nabil.stockmanger.service.impl;

import com.google.common.collect.ImmutableList;
import com.hadeer.nabil.stockmanger.dao.StockRepository;
import com.hadeer.nabil.stockmanger.dto.StockDTO;
import com.hadeer.nabil.stockmanger.model.Stock;
import com.hadeer.nabil.stockmanger.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("stockService")
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Autowired
    public StockServiceImpl(final StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    @Transactional
    public Stock updateStock(StockDTO stockDTO) {
        final String id = stockDTO.getId();
        final Optional<Stock> oldStock = stockRepository.findById(id);
        int purchasingUnits = 0;
        if (oldStock.isPresent()) {
            purchasingUnits = (stockDTO.getQuantity() < oldStock.get().getQuantity()) ?
                    oldStock.get().getPurchasingUnits() + (oldStock.get().getQuantity() - stockDTO.getQuantity()) : oldStock.get().getPurchasingUnits();
        }
        Stock newStock = Stock.builder()
                .id(stockDTO.getId())
                .productId(stockDTO.getProductId())
                .quantity(stockDTO.getQuantity())
                .timestamp(stockDTO.getTimestamp())
                .purchasingUnits(purchasingUnits)
                .version(oldStock.map(Stock::getVersion).orElse(0))
                .build();
        return stockRepository.save(newStock);
    }

    public boolean isOutdatedStock(StockDTO stockDTO) {
        boolean result = false;
        final String id = stockDTO.getId();
        final Optional<Stock> currentStock = stockRepository.findById(id);
        if (currentStock.isPresent()) {
            result = currentStock.get().getTimestamp().isAfter(stockDTO.getTimestamp());
        }
        return result;
    }

    @Override
    public Stock findMostRecentStock(String productId) {
        List<Stock> stockList = stockRepository.findByProductIdOrderByTimestampDesc(productId);
        return (stockList.size() > 0) ? stockList.get(0): new Stock();
    }

    @Override
    public List<Stock> findTopAvailableProducts(String time) {

        Optional<List<Stock>> stockList = Optional.of(stockRepository.findAllByOrderByQuantityDesc());

        final List<Stock> list = stockList.map(stocks ->
                stocks.stream().filter(stock -> stock.getTimestamp().isAfter(getTime(time)) && stock.getTimestamp().isBefore(LocalDateTime.now())).collect(Collectors.toList()).subList(0, 3)
        ).orElse(ImmutableList.of());

        return (list.size() > 3) ? list.subList(0, 2) : list;

    }

    public List<Stock> findTopSellingProducts(String time) {

        Optional<List<Stock>> stockList = Optional.of(stockRepository.findAllByOrderByPurchasingUnitsDesc());

        final List<Stock> list = stockList.map(stocks ->
                stocks.stream().filter(stock -> stock.getTimestamp().isAfter(getTime(time)) && stock.getTimestamp().isBefore(LocalDateTime.now())).collect(Collectors.toList()).subList(0, 3)
        ).orElse(ImmutableList.of());

        return (list.size() > 3) ? list.subList(0, 2) : list;

    }


    public final LocalDateTime getTime(String time) {
        LocalDateTime start;

        if (time.equalsIgnoreCase("today")) {
            start = LocalDate.now().atTime(0, 0);
        } else if (time.equalsIgnoreCase("lastMonth"))

        {
            start = LocalDate.now().atTime(0, 0).minusMonths(1);
        } else {
            start = LocalDateTime.now();
        }

        return start;
    }
}
