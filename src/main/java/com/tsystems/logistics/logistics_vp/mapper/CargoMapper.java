package com.tsystems.logistics.logistics_vp.mapper;

import com.tsystems.logistics.logistics_vp.code.model.CargoDto;
import com.tsystems.logistics.logistics_vp.entity.Cargo;
import com.tsystems.logistics.logistics_vp.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CargoMapper {

    CargoMapper INSTANCE = Mappers.getMapper(CargoMapper.class);
    CargoDto cargoToCargoDto(Cargo cargo);

    default Integer convertOrder(Order order){
        return order != null ? order.getOrderId() : null;
    }
}
