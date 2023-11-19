package com.tsystems.logistics.logistics_vp.mapper;

import com.tsystems.logistics.logistics_vp.code.model.OrderDto;
import com.tsystems.logistics.logistics_vp.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDto orderToOrderDto(Order order);
}
