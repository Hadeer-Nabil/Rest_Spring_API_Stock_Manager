package com.hadeer.nabil.stockmanger.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hadeer.nabil.stockmanger.dao.StockRepository;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO{

    private String id;

    private LocalDateTime timestamp;

    private String productId;

    private int quantity;

    public static StockDTO createStockDTO(String id, LocalDateTime timestamp, String productId, int quantity){
        return new StockDTO(id, timestamp, productId, quantity);
    }
}



