package com.tsystems.logistics.logistics_vp.mappers;

import com.tsystems.logistics.logistics_vp.code.model.LogisticianDto;
import com.tsystems.logistics.logistics_vp.entities.AuthenticationInfo;
import com.tsystems.logistics.logistics_vp.entities.Logistician;
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
