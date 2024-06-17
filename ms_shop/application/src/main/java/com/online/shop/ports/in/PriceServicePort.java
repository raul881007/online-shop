package com.online.shop.ports.in;

import com.online.shop.domain.Price;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PriceServicePort {

    List<Price> findAllPrices();
    Optional<Price> getFilteredPrices(LocalDateTime applicationDate, int productId, int brandId);
}
