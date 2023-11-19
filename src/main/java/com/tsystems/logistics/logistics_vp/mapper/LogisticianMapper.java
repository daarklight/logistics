package com.tsystems.logistics.logistics_vp.mapper;

import com.tsystems.logistics.logistics_vp.code.model.LogisticianDto;
import com.tsystems.logistics.logistics_vp.entity.AuthenticationInfo;
import com.tsystems.logistics.logistics_vp.entity.Logistician;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LogisticianMapper {

    LogisticianMapper INSTANCE = Mappers.getMapper(LogisticianMapper.class);
    LogisticianDto logisticianToLogisticianDto(Logistician logistician);

    default Integer convertAuthenticationInfo(AuthenticationInfo authenticationInfo) {
        return authenticationInfo != null ? authenticationInfo.getId() : null;
    }
}
