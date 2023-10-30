package com.tsystems.logistics.logistics_vp.repositories;

import com.tsystems.logistics.logistics_vp.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
