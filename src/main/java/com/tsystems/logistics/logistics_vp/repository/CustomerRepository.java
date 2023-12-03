package com.tsystems.logistics.logistics_vp.repository;

import com.tsystems.logistics.logistics_vp.entity.AuthenticationInfo;
import com.tsystems.logistics.logistics_vp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findCustomerByPhone(String phone);
    Customer findCustomerByEmail(String email);
    //Customer findCustomerByCustomerAuthenticationId(AuthenticationInfo authenticationInfo);
}
