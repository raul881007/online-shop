package com.online.shop.ports.in;

import com.online.shop.domain.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceServicePort {

    List<Price> findAllPrices();
    List<Price> getFilteredPrices(LocalDateTime applicationDate, int productId, int brandId);
}
