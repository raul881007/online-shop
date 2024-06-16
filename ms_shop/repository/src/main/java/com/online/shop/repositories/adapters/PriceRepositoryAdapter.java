package com.online.shop.repositories.adapters;

import com.online.shop.repositories.PriceMOJpaRepository;
import com.online.shop.domain.Price;
import com.online.shop.repositories.mappers.PriceMapper;
import com.online.shop.repositories.models.PriceMO;
import com.online.shop.ports.out.PriceRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final PriceMOJpaRepository repository;

    private final PriceMapper mapper;

    public PriceRepositoryAdapter(PriceMOJpaRepository repository, PriceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Price> findAll() {
        return repository.findAll().stream().map(p -> mapper.fromModel(p)).collect(Collectors.toList());
    }

    @Override
    public List<Price> findFilteredPrices(LocalDateTime applicationDate, int productId, int brandId) {

        List<PriceMO> priceMOList = repository.findAllFilteredPrices(applicationDate,productId,brandId);
        List<Price> prices = new ArrayList<>();
        priceMOList.forEach(priceMO -> prices.add(mapper.fromModel(priceMO)));
        return prices;
    }

}
