package com.tsystems.logistics.logistics_vp.mappers;

import com.tsystems.logistics.logistics_vp.code.model.TruckDto;
import com.tsystems.logistics.logistics_vp.entities.Truck;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TruckMapper {
    TruckMapper INSTANCE = Mappers.getMapper(TruckMapper.class);
    TruckDto truckToTruckDto(Truck truck);
}
