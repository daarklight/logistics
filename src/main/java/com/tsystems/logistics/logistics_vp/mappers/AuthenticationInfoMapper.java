package com.tsystems.logistics.logistics_vp.mappers;

import com.tsystems.logistics.logistics_vp.code.model.AuthenticationInfoDto;
import com.tsystems.logistics.logistics_vp.code.model.TruckDto;
import com.tsystems.logistics.logistics_vp.entities.AuthenticationInfo;
import com.tsystems.logistics.logistics_vp.entities.Truck;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthenticationInfoMapper {
    AuthenticationInfoMapper INSTANCE = Mappers.getMapper(AuthenticationInfoMapper.class);
    AuthenticationInfoDto authenticationInfoToAuthenticationInfoDto(AuthenticationInfo authenticationInfo);
}
