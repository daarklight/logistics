package com.tsystems.logistics.logistics_vp.services;

import com.tsystems.logistics.logistics_vp.code.model.CreateCustomerDto;
import com.tsystems.logistics.logistics_vp.code.model.CustomerDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateCustomerDto;
import com.tsystems.logistics.logistics_vp.entities.AuthenticationInfo;
import com.tsystems.logistics.logistics_vp.entities.Customer;
import com.tsystems.logistics.logistics_vp.mappers.CustomerMapper;
import com.tsystems.logistics.logistics_vp.repositories.AuthenticationInfoRepository;
import com.tsystems.logistics.logistics_vp.repositories.CustomerRepository;
import com.tsystems.logistics.logistics_vp.repositories.customized.CustomizedCustomerRepository;
import com.tsystems.logistics.logistics_vp.services.interfaces.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final AuthenticationInfoRepository authenticationInfoRepository;
    private final CustomerRepository customerRepository;
    private final CustomizedCustomerRepository customizedCustomerRepository;


    @Override
    public List<CustomerDto> customersFindAll() {
        return customerRepository.findAll().stream().map(customer -> customerDto(customer)).toList();
    }

    @Override
    public CustomerDto customerCreate(CreateCustomerDto customerDto) {
        AuthenticationInfo authenticationInfo = authenticationInfoRepository.findById(
                customerDto.getCustomerAuthenticationId()).orElseThrow();
        Customer customer = Customer.builder()
                .customerAuthenticationId(authenticationInfo)
                .customerName(customerDto.getCustomerName())
                .phone(customerDto.getPhone())
                .email(customerDto.getEmail())
                .build();
        customerRepository.save(customer);
        return customerDto(customer);
    }

    @Override
    public CustomerDto customerUpdate(Integer customerId, UpdateCustomerDto customerDto) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setPhone(customerDto.getPhone());
        customer.setEmail(customerDto.getEmail());
        customerRepository.save(customer);
        return customerDto(customer);
    }

    @Override
    public void customerDelete(Integer customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public CustomerDto customerFindById(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        return customerDto(customer);
    }

    @Override
    public CustomerDto customerFindByPhone(String phone) {
        Customer customer = customizedCustomerRepository.findCustomerByPhone(phone);
        return customerDto(customer);
    }

    @Override
    public CustomerDto customerFindByEmail(String email) {
        Customer customer = customizedCustomerRepository.findCustomerByEmail(email);
        return customerDto(customer);
    }

    private CustomerDto customerDto(Customer customer) {
        return CustomerMapper.INSTANCE.customerToCustomerDto(customer);
    }
}
