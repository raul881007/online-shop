package com.online.shop.ports.out;

import com.online.shop.domain.Price;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface PriceRepositoryPort {
    List<Price> findAll();

    List<Price> findFilteredPrices(LocalDateTime applicationDate, int productId, int brandId);
}
