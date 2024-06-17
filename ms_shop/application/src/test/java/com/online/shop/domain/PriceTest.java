package com.online.shop.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PriceTest {
    Price price;
    LocalDateTime applicationTime;

    @BeforeEach
    public void before() {

        price = getTestPrice();
        applicationTime = LocalDateTime.of(2024,6,18,10,20,10);
    }

    @Test
    @DisplayName("Should return true when price is  > 30")
    void priceCorrect() {
        assertTrue(price.getPrice() > 30);
    }

    @Test
    @DisplayName("Should return true if date is date is valid")
    void existPrice() {
        assertTrue(price.getStartDate().isBefore(applicationTime) && price.getEndDate().isAfter(applicationTime));
    }


    protected Price getTestPrice(){
        return Price.builder()
                .price(40.0F)
                .brandId(1)
                .priceList(2)
                .priority(3)
                .curr("EUR")
                .startDate(LocalDateTime.of(2024,6,12,10,20,10))
                .endDate(LocalDateTime.of(2024,8,12,10,20,10))
                .build();
    }
}
