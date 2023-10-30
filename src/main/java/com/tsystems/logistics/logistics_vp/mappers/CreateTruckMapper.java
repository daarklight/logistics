package com.tsystems.logistics.logistics_vp.mappers;

import com.tsystems.logistics.logistics_vp.code.model.CreateTruckDto;
import com.tsystems.logistics.logistics_vp.code.model.TruckDto;
import com.tsystems.logistics.logistics_vp.entities.Truck;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateTruckMapper {
    //https://mapstruct.org/
    CreateTruckMapper INSTANCE = Mappers.getMapper(CreateTruckMapper.class);
    CreateTruckDto truckToCreateTruckDto(Truck truck);
}
