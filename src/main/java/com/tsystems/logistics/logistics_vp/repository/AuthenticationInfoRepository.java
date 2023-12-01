package com.tsystems.logistics.logistics_vp.repository;

import com.tsystems.logistics.logistics_vp.entity.AuthenticationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthenticationInfoRepository extends JpaRepository<AuthenticationInfo, Integer> {
    AuthenticationInfo findByUsername(String username);
    //Optional<AuthenticationInfo> findByUsername(String username);
}
