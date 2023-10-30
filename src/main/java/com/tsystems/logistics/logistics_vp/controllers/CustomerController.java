package com.tsystems.logistics.logistics_vp.controllers;

import com.tsystems.logistics.logistics_vp.code.api.CustomersApi;
import com.tsystems.logistics.logistics_vp.code.model.CreateCustomerDto;
import com.tsystems.logistics.logistics_vp.code.model.CustomerDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateCustomerDto;
import com.tsystems.logistics.logistics_vp.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController implements CustomersApi {
    @Override
    public ResponseEntity<CustomerDto> customerCreate(CreateCustomerDto createCustomerDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> customerDelete(Integer customerId) {
        return null;
    }

    @Override
    public ResponseEntity<CustomerDto> customerFindByEmail(String fields) {
        return null;
    }

    @Override
    public ResponseEntity<CustomerDto> customerFindById(Integer customerId) {
        return null;
    }

    @Override
    public ResponseEntity<CustomerDto> customerFindByPhone(String fields) {
        return null;
    }

    @Override
    public ResponseEntity<CustomerDto> customerUpdate(Integer customerId, UpdateCustomerDto updateCustomerDto) {
        return null;
    }

    @Override
    public ResponseEntity<List<CustomerDto>> customersFindAll() {
        return null;
    }
}
