package com.hadeer.nabil.stockmanger.dao;

import com.hadeer.nabil.stockmanger.model.Stock;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, String> {

    List<Stock> findByProductIdOrderByTimestampDesc(String productId);

    List<Stock> findAllByOrderByQuantityDesc();

    List<Stock> findAllByOrderByPurchasingUnitsDesc();
}
