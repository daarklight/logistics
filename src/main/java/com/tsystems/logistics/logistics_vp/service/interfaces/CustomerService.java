package com.tsystems.logistics.logistics_vp.service.interfaces;

import com.tsystems.logistics.logistics_vp.code.model.AuthenticationInfoDto;
import com.tsystems.logistics.logistics_vp.code.model.CreateCustomerDto;
import com.tsystems.logistics.logistics_vp.code.model.CustomerDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateCustomerDto;
import com.tsystems.logistics.logistics_vp.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> customersFindAll();
    CustomerDto customerCreate(CreateCustomerDto customerDto);
    CustomerDto customerUpdate(Integer customerId, UpdateCustomerDto customerDto);
    void customerDelete(Integer customerId);
    CustomerDto customerFindById(Integer customerId);
    CustomerDto customerFindByPhone(String phone);
    CustomerDto customerFindByEmail(String email);
    CustomerDto customerFindByUsername(String username);
    Customer getCustomerFromDb(Integer customerId);
}
