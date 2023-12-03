package com.tsystems.logistics.logistics_vp.mapper;

import com.tsystems.logistics.logistics_vp.code.model.AuthenticationInfoDto;
import com.tsystems.logistics.logistics_vp.entity.AuthenticationInfo;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthenticationInfoMapper {
    AuthenticationInfoMapper INSTANCE = Mappers.getMapper(AuthenticationInfoMapper.class);
    @ValueMapping(source = "ROLE_ADMIN", target = "ADMIN")
    @ValueMapping(source = "ROLE_DRIVER", target = "DRIVER")
    @ValueMapping(source = "ROLE_LOGISTICIAN", target = "LOGISTICIAN")
    @ValueMapping(source = "ROLE_CUSTOMER", target = "CUSTOMER")
    AuthenticationInfoDto authenticationInfoToAuthenticationInfoDto(AuthenticationInfo authenticationInfo);
}
