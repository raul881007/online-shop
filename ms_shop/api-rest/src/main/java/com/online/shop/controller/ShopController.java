package com.online.shop.controller;

import com.online.shop.domain.ErrorResponse;
import com.online.shop.domain.Price;
import com.online.shop.ports.in.PriceServicePort;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@CrossOrigin()
@RequestMapping("/api/prices")
@Slf4j
public class ShopController {

    private final PriceServicePort priceServicePort;

    /**
     * Get method that gets filtered price
     * @return ResponseEntity.ok() with of price
     */
    @GetMapping
    @ApiResponse(responseCode = "200", description = "Price found for current input data", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = Price.class)))
    @ApiResponse(responseCode = "204", description = "No prices found for the current input parameters", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(mediaType = "application/json"))
    private ResponseEntity<Price> getFilteredPrice(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  final LocalDateTime date,
                                                            @RequestParam int productId,
                                                            @RequestParam int brandId){
        log.debug("getFilteredPrice(date={},productId={},brandId={})", date, productId, brandId);

        return priceServicePort.getFilteredPrices(date,productId,brandId).map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.noContent().build());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public Object handleValidationExceptions(
            Exception ex) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .error("There is an error")
                .message(ex.getMessage())
                .build();
    }

}
