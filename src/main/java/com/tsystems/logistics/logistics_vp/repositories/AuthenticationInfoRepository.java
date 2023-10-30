package com.tsystems.logistics.logistics_vp.repositories;

import com.tsystems.logistics.logistics_vp.entities.AuthenticationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationInfoRepository extends JpaRepository<AuthenticationInfo, Integer> {
}
