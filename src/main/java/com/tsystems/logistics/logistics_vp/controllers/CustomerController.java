package com.tsystems.logistics.logistics_vp.controllers;

import com.tsystems.logistics.logistics_vp.code.api.CustomersApi;
import com.tsystems.logistics.logistics_vp.code.model.CreateCustomerDto;
import com.tsystems.logistics.logistics_vp.code.model.CustomerDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateCustomerDto;
import com.tsystems.logistics.logistics_vp.services.interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController implements CustomersApi {

    private final CustomerService customerService;

    @Override
    public ResponseEntity<CustomerDto> customerCreate(CreateCustomerDto createCustomerDto) {
        CustomerDto resultCustomerDto = customerService.customerCreate(createCustomerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resultCustomerDto);
    }

    @Override
    public ResponseEntity<Void> customerDelete(Integer customerId) {
        customerService.customerDelete(customerId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Override
    public ResponseEntity<CustomerDto> customerFindByEmail(String email) {
        CustomerDto resultCustomerDto = customerService.customerFindByEmail(email);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultCustomerDto);
    }

    @Override
    public ResponseEntity<CustomerDto> customerFindById(Integer customerId) {
        CustomerDto resultCustomerDto = customerService.customerFindById(customerId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultCustomerDto);
    }

    @Override
    public ResponseEntity<CustomerDto> customerFindByPhone(String phone) {
        CustomerDto resultCustomerDto = customerService.customerFindByPhone(phone);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultCustomerDto);
    }

    @Override
    public ResponseEntity<CustomerDto> customerUpdate(Integer customerId, UpdateCustomerDto updateCustomerDto) {
        CustomerDto resultCustomerDto = customerService.customerUpdate(customerId, updateCustomerDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultCustomerDto);
    }

    @Override
    public ResponseEntity<List<CustomerDto>> customersFindAll() {
        List<CustomerDto> allResultCustomerDtos = customerService.customersFindAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCustomerDtos);
    }
}
