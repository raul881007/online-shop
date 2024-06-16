package com.online.config;

import com.online.shop.ports.in.PriceServicePort;
import com.online.shop.ports.out.PriceRepositoryPort;
import com.online.shop.services.PriceService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.online.shop"})
@EnableJpaRepositories(basePackages={"com.online.shop.repositories"})
@EntityScan(basePackages={"com.online.shop.repositories.models"})
@EnableTransactionManagement
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    PriceServicePort pricesAPIService(PriceRepositoryPort priceRepository) {
        return new PriceService(priceRepository);
    }
}