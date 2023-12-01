package com.tsystems.logistics.logistics_vp.repository;

import com.tsystems.logistics.logistics_vp.entity.AuthenticationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationInfoRepository extends JpaRepository<AuthenticationInfo, Integer> {
    //AuthenticationInfo findByLogin(String login);
    AuthenticationInfo findByUsername(String username);
    //Optional<AuthenticationInfo> findByUsername(String username);
}
