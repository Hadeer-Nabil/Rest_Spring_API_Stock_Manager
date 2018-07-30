package com.hadeer.nabil.stockmanger.control;

import com.hadeer.nabil.stockmanger.dto.SoldProductDTO;
import com.hadeer.nabil.stockmanger.dto.StockDTO;
import com.hadeer.nabil.stockmanger.dto.StockQueryDTO;
import com.hadeer.nabil.stockmanger.dto.StockStatisticsQueryDTO;
import com.hadeer.nabil.stockmanger.model.Stock;
import com.hadeer.nabil.stockmanger.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

@RestController
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(final StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/stock")
    public StockQueryDTO getStockByProductId(@RequestParam("productId") String productId, HttpServletRequest request) {
        long requestTimestamp = request.getSession().getCreationTime();
        LocalDateTime triggerTime =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(requestTimestamp),
                        TimeZone.getDefault().toZoneId());
        final Stock mostRecentStock = stockService.findMostRecentStock(productId);
        return StockQueryDTO.builder()
                .productId(productId)
                .requestTimestamp(triggerTime)
                .stock(StockDTO.builder()
                        .id(mostRecentStock.getId())
                        .quantity(mostRecentStock.getQuantity())
                        .timestamp(mostRecentStock.getTimestamp())
                        .build()
                ).build();
    }


    @GetMapping("/statistics")
    public StockStatisticsQueryDTO getStatistics(@RequestParam("time") String time, HttpServletRequest request) {
        long requestTimestamp = request.getSession().getCreationTime();
        LocalDateTime triggerTime =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(requestTimestamp),
                        TimeZone.getDefault().toZoneId());

        final List<Stock> topAvailableProducts = stockService.findTopAvailableProducts(time);

        final List<Stock> topSellingProducts = stockService.findTopSellingProducts(time);

        List<StockDTO> topAvailableProductsAsDto = topAvailableProducts.stream().map(
                s -> StockDTO.createStockDTO(s.getId(), s.getTimestamp(), s.getProductId(), s.getQuantity())).collect(Collectors.toList());


        List<SoldProductDTO> topSellingProductsAsDto = topSellingProducts.stream().map(
                s -> SoldProductDTO.createSoldStockDTO(s.getProductId(), s.getPurchasingUnits())).collect(Collectors.toList());


        return StockStatisticsQueryDTO.builder()
                .requestTimestamp(triggerTime)
                .range(time)
                .topAvailableProducts(topAvailableProductsAsDto)
                .topSellingProducts(topSellingProductsAsDto)
                .build();
    }


    @PostMapping("/updateStock")
    public ResponseEntity<?> update(@RequestBody StockDTO stockDTO) {

        return stockService.isOutdatedStock(stockDTO) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(stockService.updateStock(stockDTO), HttpStatus.CREATED);
    }

}
