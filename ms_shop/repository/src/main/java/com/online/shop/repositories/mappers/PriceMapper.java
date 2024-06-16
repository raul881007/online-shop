package com.online.shop.repositories.mappers;

import com.online.shop.repositories.models.PriceMO;
import com.online.shop.domain.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(source = "start_date", target = "startDate")
    @Mapping(source = "end_date", target = "endDate")
    Price fromModel(PriceMO price);

}
