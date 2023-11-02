package com.tsystems.logistics.logistics_vp.services;

import com.tsystems.logistics.logistics_vp.code.model.CreateCustomerDto;
import com.tsystems.logistics.logistics_vp.code.model.CustomerDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateCustomerDto;
import com.tsystems.logistics.logistics_vp.entities.Customer;
import com.tsystems.logistics.logistics_vp.mappers.CustomerMapper;
import com.tsystems.logistics.logistics_vp.repositories.CustomerRepository;
import com.tsystems.logistics.logistics_vp.services.interfaces.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;


    @Override
    public List<CustomerDto> customersFindAll() {
        return customerRepository.findAll().stream().map(customer -> customerDto(customer)).toList();
    }

    @Override
    public CustomerDto customerCreate(CreateCustomerDto customerDto) {
        return null;
    }

    @Override
    public CustomerDto customerUpdate(Integer customerId, UpdateCustomerDto customerDto) {
        return null;
    }

    @Override
    public void customerDelete(Integer customerId) {

    }

    @Override
    public CustomerDto customerFindById(Integer customerId) {
        return null;
    }

    @Override
    public CustomerDto customerFindByPhone(String phone) {
        return null;
    }

    @Override
    public CustomerDto customerFindByEmail(String phone) {
        return null;
    }

    private CustomerDto customerDto(Customer customer) {
        return CustomerMapper.INSTANCE.customerToCustomerDto(customer);
    }
}
