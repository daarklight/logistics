package com.tsystems.logistics.logistics_vp.repositories.customized;

import com.tsystems.logistics.logistics_vp.entities.AuthenticationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomizedAuthenticationInfoRepository extends JpaRepository<AuthenticationInfo, Integer> {
    AuthenticationInfo findByLogin(String login);
}
