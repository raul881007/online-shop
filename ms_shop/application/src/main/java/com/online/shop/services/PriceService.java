package com.online.shop.services;

import com.online.shop.domain.Price;
import com.online.shop.ports.in.PriceServicePort;
import com.online.shop.ports.out.PriceRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Transactional
public class PriceService implements PriceServicePort {

    private final PriceRepositoryPort priceRepositoryPort;


    /**
     * Gets the prices list from database
     * @return PricesList stored in database
     * @author Raul Herrera
     */
    @Override
    public List<Price> findAllPrices() {
        return priceRepositoryPort.findAll();
    }

    /**
     * Gets the filtered price by entry parameter
     * @param applicationDate local price date
     * @param productId Custom product identifier
     * @param brandId Custom brand identifier
     * @return Optional price with max priority
     * @author Raul Herrera
     */
    @Override
    public Optional<Price> getFilteredPrices(LocalDateTime applicationDate, int productId, int brandId){
        log.debug("getFilteredPrices({}, {}, {})", applicationDate, productId, brandId);

        var prices = priceRepositoryPort.findFilteredPrices(applicationDate, productId, brandId);

        return prices.stream().max(Comparator.comparing(Price::getPriority));

    }


}
