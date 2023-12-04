package com.tsystems.logistics.logistics_vp.service.interfaces;

import com.tsystems.logistics.logistics_vp.code.model.AuthenticationInfoDto;
import com.tsystems.logistics.logistics_vp.code.model.AuthenticationInfoToSendDto;
import com.tsystems.logistics.logistics_vp.entity.AuthenticationInfo;

import java.util.List;

public interface AuthenticationInfoService {
    List<AuthenticationInfoDto> authenticationInfoFindAll();
    AuthenticationInfoDto authenticationInfoCreate(AuthenticationInfoDto authenticationInfoDto);
    AuthenticationInfoDto authenticationInfoUpdate(Integer id, AuthenticationInfoDto authenticationInfoDto);
    void authenticationInfoDelete(Integer id);
    AuthenticationInfoDto authenticationInfoFindById(Integer id);
    AuthenticationInfoDto authenticationInfoFindByUsername(String login);
    String authenticationInfoSend(AuthenticationInfoToSendDto authenticationInfoToSendDto);
    AuthenticationInfo getAuthenticationInfoFromDb(Integer id);
}
