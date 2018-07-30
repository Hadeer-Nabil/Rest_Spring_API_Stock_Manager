package com.hadeer.nabil.stockmanger.service;

import com.hadeer.nabil.stockmanger.dto.SoldProductDTO;
import com.hadeer.nabil.stockmanger.dto.StockDTO;
import com.hadeer.nabil.stockmanger.model.Stock;

import java.util.List;

public interface StockService {

    Stock updateStock(StockDTO stockDTO);

    boolean isOutdatedStock(StockDTO stockDTO);

    Stock findMostRecentStock(String productId);

    List<Stock> findTopAvailableProducts(String range);

    List<Stock> findTopSellingProducts(String range);
}
