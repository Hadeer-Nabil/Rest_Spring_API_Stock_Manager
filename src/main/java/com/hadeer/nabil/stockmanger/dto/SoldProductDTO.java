package com.hadeer.nabil.stockmanger.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)

public class SoldProductDTO {

    private String productId;

    private int itemsSold;

    public static SoldProductDTO createSoldStockDTO(String productId,int itemsSold){
        return new SoldProductDTO( productId, itemsSold);
    }
}
