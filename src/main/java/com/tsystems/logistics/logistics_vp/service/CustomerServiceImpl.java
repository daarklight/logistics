package com.tsystems.logistics.logistics_vp.service;

import com.tsystems.logistics.logistics_vp.code.model.CreateCustomerDto;
import com.tsystems.logistics.logistics_vp.code.model.CustomerDto;
import com.tsystems.logistics.logistics_vp.code.model.UpdateCustomerDto;
import com.tsystems.logistics.logistics_vp.entity.AuthenticationInfo;
import com.tsystems.logistics.logistics_vp.entity.Customer;
import com.tsystems.logistics.logistics_vp.exceptions.custom.NoSuchCustomerException;
import com.tsystems.logistics.logistics_vp.mapper.CustomerMapper;
import com.tsystems.logistics.logistics_vp.repository.AuthenticationInfoRepository;
import com.tsystems.logistics.logistics_vp.repository.CustomerRepository;
import com.tsystems.logistics.logistics_vp.service.interfaces.CustomerService;
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
        Customer customer = getCustomerFromDb(customerId);
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setPhone(customerDto.getPhone());
        customer.setEmail(customerDto.getEmail());
        customerRepository.save(customer);
        return customerDto(customer);
    }

    @Override
    public void customerDelete(Integer customerId) {
        getCustomerFromDb(customerId);
        customerRepository.deleteById(customerId);
    }

    @Override
    public CustomerDto customerFindById(Integer customerId) {
        return customerDto(getCustomerFromDb(customerId));
    }

    @Override
    public CustomerDto customerFindByPhone(String phone) {
        Customer customer = customerRepository.findCustomerByPhone(phone);
        return customerDto(customer);
    }

    @Override
    public CustomerDto customerFindByEmail(String email) {
        Customer customer = customerRepository.findCustomerByEmail(email);
        return customerDto(customer);
    }

    @Override
    public CustomerDto customerFindByUsername(String username) {
        AuthenticationInfo authenticationInfo = authenticationInfoRepository.findByUsername(username);
        Customer customer = authenticationInfo.getCustomer();
        return customerDto(customer);
    }

    @Override
    public Customer getCustomerFromDb(Integer customerId) {
        return customerRepository.findById(customerId).orElseThrow(() ->
                new NoSuchCustomerException("This customer does not exist in database"));
    }

    private CustomerDto customerDto(Customer customer) {
        return CustomerMapper.INSTANCE.customerToCustomerDto(customer);
    }
}
