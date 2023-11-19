package com.tsystems.logistics.logistics_vp.repository.customized;

import com.tsystems.logistics.logistics_vp.entity.AuthenticationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomizedAuthenticationInfoRepository extends JpaRepository<AuthenticationInfo, Integer> {
    AuthenticationInfo findByLogin(String login);
}
