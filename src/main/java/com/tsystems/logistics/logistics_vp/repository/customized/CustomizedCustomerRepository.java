package com.tsystems.logistics.logistics_vp.repository.customized;

import com.tsystems.logistics.logistics_vp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomizedCustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findCustomerByPhone(String phone);
    Customer findCustomerByEmail(String email);
}
