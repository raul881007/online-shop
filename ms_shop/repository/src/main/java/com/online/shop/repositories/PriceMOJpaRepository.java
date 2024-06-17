package com.online.shop.repositories;

import com.online.shop.repositories.models.PriceMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceMOJpaRepository extends JpaRepository<PriceMO, Long> {
    @Query (value = "Select p from PriceMO p " +
            "where p.start_date <= :application_date and p.end_date >= :application_date " +
            "and p.productId = :product_id " +
            "and p.brandId = :brand_id")
    List<PriceMO> findAllFilteredPrices(@Param("application_date") LocalDateTime applicationDate, @Param("product_id") int productId,@Param("brand_id") int brandId);
}

