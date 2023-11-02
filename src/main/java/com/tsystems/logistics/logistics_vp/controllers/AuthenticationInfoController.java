package com.tsystems.logistics.logistics_vp.controllers;

import com.tsystems.logistics.logistics_vp.code.api.AuthenticationInfoApi;
import com.tsystems.logistics.logistics_vp.code.model.AuthenticationInfoDto;
import com.tsystems.logistics.logistics_vp.repositories.AuthenticationInfoRepository;
import com.tsystems.logistics.logistics_vp.services.interfaces.AuthenticationInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthenticationInfoController implements AuthenticationInfoApi {

    private final AuthenticationInfoService authenticationInfoService;
    @Override
    public ResponseEntity<AuthenticationInfoDto> authenticationInfoCreate(AuthenticationInfoDto authenticationInfoDto) {
        AuthenticationInfoDto authenticationInfoResult = authenticationInfoService
                .authenticationInfoCreate(authenticationInfoDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authenticationInfoResult);
    }

    @Override
    public ResponseEntity<Void> authenticationInfoDelete(Integer id) {
        authenticationInfoService.authenticationInfoDelete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Override
    public ResponseEntity<List<AuthenticationInfoDto>> authenticationInfoFindAll() {
        List<AuthenticationInfoDto> allAuthenticationInfo = authenticationInfoService.authenticationInfoFindAll();
        return ResponseEntity
                .ok(allAuthenticationInfo);
    }

    @Override
    public ResponseEntity<AuthenticationInfoDto> authenticationInfoFindById(Integer id) {
        AuthenticationInfoDto authenticationInfoDto = authenticationInfoService.authenticationInfoFindById(id);
        return ResponseEntity
                .ok(authenticationInfoDto);
    }

    @Override
    public ResponseEntity<AuthenticationInfoDto> authenticationInfoFindByLogin(String login) {
        AuthenticationInfoDto authenticationInfoDto = authenticationInfoService.authenticationInfoFindByLogin(login);
        return ResponseEntity
                .ok(authenticationInfoDto);
    }

    @Override
    public ResponseEntity<AuthenticationInfoDto> authenticationInfoUpdate(Integer id, AuthenticationInfoDto authenticationInfoDto) {
        AuthenticationInfoDto authenticationInfoDtoUpdate = authenticationInfoService
                .authenticationInfoUpdate(id, authenticationInfoDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authenticationInfoDtoUpdate);
    }
}
