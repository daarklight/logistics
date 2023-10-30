package com.tsystems.logistics.logistics_vp.controllers;

import com.tsystems.logistics.logistics_vp.code.api.AuthenticationInfoApi;
import com.tsystems.logistics.logistics_vp.code.model.AuthenticationInfoDto;
import com.tsystems.logistics.logistics_vp.repositories.AuthenticationInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthenticationInfoController implements AuthenticationInfoApi {

    @Override
    public ResponseEntity<AuthenticationInfoDto> authenticationInfoCreate(AuthenticationInfoDto authenticationInfoDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> authenticationInfoDelete(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<AuthenticationInfoDto>> authenticationInfoFindAll() {
        return null;
    }

    @Override
    public ResponseEntity<AuthenticationInfoDto> authenticationInfoFindById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<AuthenticationInfoDto> authenticationInfoFindByLogin(String login) {
        return null;
    }

    @Override
    public ResponseEntity<AuthenticationInfoDto> authenticationInfoUpdate(Integer id, AuthenticationInfoDto authenticationInfoDto) {
        return null;
    }
}
