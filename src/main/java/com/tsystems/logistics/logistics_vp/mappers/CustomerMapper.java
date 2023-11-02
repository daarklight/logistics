package com.tsystems.logistics.logistics_vp.mappers;

import com.tsystems.logistics.logistics_vp.code.model.CustomerDto;
import com.tsystems.logistics.logistics_vp.entities.AuthenticationInfo;
import com.tsystems.logistics.logistics_vp.entities.Customer;
import com.tsystems.logistics.logistics_vp.entities.Truck;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    CustomerDto customerToCustomerDto(Customer customer);

    default Integer convertAuthenticationInfo(AuthenticationInfo authenticationInfo){
        return authenticationInfo != null ? authenticationInfo.getId() : null;
    }
}
