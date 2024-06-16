package com.online.shop.controller;

import com.online.shop.domain.Price;
import com.online.shop.ports.in.PriceServicePort;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin()
@RequestMapping("/api/prices")
@Slf4j
public class ShopController {

    private final PriceServicePort priceServicePort;

    /**
     * Get method that gets all prices
     * @return ResponseEntity.ok() with a list of price
     */
    @GetMapping
    private ResponseEntity<List<Price>> getFilteredPrices(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  final LocalDateTime date,
                                                            @RequestParam int productId,
                                                            @RequestParam int brandId){
        return ResponseEntity.ok(priceServicePort.getFilteredPrices(date,productId,brandId));
    }


}
