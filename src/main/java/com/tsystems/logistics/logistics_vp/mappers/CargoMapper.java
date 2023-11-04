package com.tsystems.logistics.logistics_vp.mappers;

import com.tsystems.logistics.logistics_vp.code.model.CargoDto;
import com.tsystems.logistics.logistics_vp.entities.Cargo;
import com.tsystems.logistics.logistics_vp.entities.Order;
import com.tsystems.logistics.logistics_vp.entities.Truck;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper
public interface CargoMapper {

    CargoMapper INSTANCE = Mappers.getMapper(CargoMapper.class);
    CargoDto cargoToCargoDto(Cargo cargo);

    default Integer convertOrder(Order order){
        return order != null ? order.getOrderId() : null;
    }
}
