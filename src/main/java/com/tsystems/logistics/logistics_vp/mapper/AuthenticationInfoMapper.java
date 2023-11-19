package com.tsystems.logistics.logistics_vp.mapper;

import com.tsystems.logistics.logistics_vp.code.model.AuthenticationInfoDto;
import com.tsystems.logistics.logistics_vp.entity.AuthenticationInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthenticationInfoMapper {
    AuthenticationInfoMapper INSTANCE = Mappers.getMapper(AuthenticationInfoMapper.class);
    AuthenticationInfoDto authenticationInfoToAuthenticationInfoDto(AuthenticationInfo authenticationInfo);
}
