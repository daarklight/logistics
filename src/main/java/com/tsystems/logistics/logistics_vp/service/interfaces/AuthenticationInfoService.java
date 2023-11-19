package com.tsystems.logistics.logistics_vp.service.interfaces;

import com.tsystems.logistics.logistics_vp.code.model.AuthenticationInfoDto;

import java.util.List;

public interface AuthenticationInfoService {
    List<AuthenticationInfoDto> authenticationInfoFindAll();
    AuthenticationInfoDto authenticationInfoCreate(AuthenticationInfoDto authenticationInfoDto);
    AuthenticationInfoDto authenticationInfoUpdate(Integer id, AuthenticationInfoDto authenticationInfoDto);
    void authenticationInfoDelete(Integer id);
    AuthenticationInfoDto authenticationInfoFindById(Integer id);
    AuthenticationInfoDto authenticationInfoFindByLogin(String login);
}
