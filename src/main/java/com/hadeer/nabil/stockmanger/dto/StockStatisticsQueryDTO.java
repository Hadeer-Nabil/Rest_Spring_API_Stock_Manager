package com.hadeer.nabil.stockmanger.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockStatisticsQueryDTO {

    private LocalDateTime requestTimestamp;

    private String range;

    private List<StockDTO> topAvailableProducts;

    private List<SoldProductDTO> topSellingProducts;
}
