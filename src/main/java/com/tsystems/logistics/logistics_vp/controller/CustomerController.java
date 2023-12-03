package com.tsystems.logistics.logistics_vp.controller;

import com.tsystems.logistics.logistics_vp.code.api.CustomersApi;
import com.tsystems.logistics.logistics_vp.code.model.AuthenticationInfoDto;
import com.tsystems.logistics.logistics_vp.code.model.CreateCustomerDto;
import com.tsystems.logistics.logistics_vp.code.model.CustomerDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateCustomerDto;
import com.tsystems.logistics.logistics_vp.service.interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@Log4j2
public class CustomerController implements CustomersApi {

    private final CustomerService customerService;

    @Override
    public ResponseEntity<CustomerDto> customerCreate(CreateCustomerDto createCustomerDto) {
        log.info("Start to register new customer");
        CustomerDto resultCustomerDto = customerService.customerCreate(createCustomerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resultCustomerDto);
    }

    @Override
    public ResponseEntity<Void> customerDelete(Integer customerId) {
        log.info("Start to delete customer");
        customerService.customerDelete(customerId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Override
    public ResponseEntity<CustomerDto> customerFindByEmail(String email) {
        log.info("Start to find customer by email");
        CustomerDto resultCustomerDto = customerService.customerFindByEmail(email);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultCustomerDto);
    }

    @Override
    public ResponseEntity<CustomerDto> customerFindById(Integer customerId) {
        log.info("Start to find customer by id");
        CustomerDto resultCustomerDto = customerService.customerFindById(customerId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultCustomerDto);
    }

    @Override
    public ResponseEntity<CustomerDto> customerFindByPhone(String phone) {
        log.info("Start to find customer by phone");
        CustomerDto resultCustomerDto = customerService.customerFindByPhone(phone);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultCustomerDto);
    }

    @Override
    public ResponseEntity<CustomerDto> customerUpdate(Integer customerId, UpdateCustomerDto updateCustomerDto) {
        log.info("Start to update customer");
        CustomerDto resultCustomerDto = customerService.customerUpdate(customerId, updateCustomerDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultCustomerDto);
    }

    @Override
    public ResponseEntity<List<CustomerDto>> customersFindAll() {
        log.info("Start to find all customers");
        List<CustomerDto> allResultCustomerDtos = customerService.customersFindAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultCustomerDtos);
    }

    @Override
    public ResponseEntity<CustomerDto> customerFindByUsername(String username) {
        log.info("Start to find customer by username");
        CustomerDto resultCustomerDto = customerService.customerFindByUsername(username);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultCustomerDto);
    }
}
