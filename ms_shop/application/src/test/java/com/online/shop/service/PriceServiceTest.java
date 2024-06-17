package com.online.shop.service;

import com.online.shop.domain.Price;
import com.online.shop.ports.in.PriceServicePort;
import com.online.shop.ports.out.PriceRepositoryPort;
import com.online.shop.services.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

public class PriceServiceTest{

    @Mock
    private PriceRepositoryPort droneRepositoryPort;

    private PriceServicePort service;

    LocalDateTime applicationTime;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
        service = new PriceService(droneRepositoryPort);
        applicationTime = LocalDateTime.of(2024,6,18,10,20,10);
    }

    @Test
    void shouldReturnAPrice(){
        Price priceDto = getTestPrice();
        var priceList = new ArrayList<Price>();
        priceList.add(priceDto);
        given(droneRepositoryPort.findFilteredPrices(any(),anyInt(),anyInt())).willReturn(priceList);
        Optional<Price> priceResp = service.getFilteredPrices(applicationTime,1,1);
        assertTrue(priceResp.isPresent());
        assertEquals(priceResp.get().getPrice(), 40.0F);
        assertEquals(priceResp.get().getBrandId(), 1);
        assertEquals(priceResp.get().getPriceList(), 2);
        assertEquals(priceResp.get().getPriority(), 3);
        assertEquals(priceResp.get().getCurr(), "EUR");
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
