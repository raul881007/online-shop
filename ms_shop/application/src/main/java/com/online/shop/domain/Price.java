package com.online.shop.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Price {
    private int brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int productId;
    private int priceList;
    private int priority;
    private double price;
    private String curr;

}
