package com.tsystems.logistics.logistics_vp.mappers;

import com.tsystems.logistics.logistics_vp.code.model.DriverDto;
import com.tsystems.logistics.logistics_vp.entities.AuthenticationInfo;
import com.tsystems.logistics.logistics_vp.entities.Driver;
import com.tsystems.logistics.logistics_vp.entities.Order;
import com.tsystems.logistics.logistics_vp.entities.Truck;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DriverMapper {

    DriverMapper INSTANCE = Mappers.getMapper(DriverMapper.class);
    DriverDto driverToDriverDto(Driver driver);

    default Integer convertAuthenticationInfo(AuthenticationInfo authenticationInfo){
        return authenticationInfo != null ? authenticationInfo.getId() : null;
    }

    default String convertTruck(Truck truck){
        return truck != null ? truck.getNumber() : null;
    }

    default Integer convertOrder(Order order){
        return order != null ? order.getOrderId() : null;
    }
}
