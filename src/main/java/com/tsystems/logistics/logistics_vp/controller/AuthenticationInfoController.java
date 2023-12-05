package com.tsystems.logistics.logistics_vp.controller;

import com.tsystems.logistics.logistics_vp.code.api.AuthenticationInfoApi;
import com.tsystems.logistics.logistics_vp.code.model.AuthenticationInfoDto;
//import com.tsystems.logistics.logistics_vp.code.model.AuthenticationInfoToSendDto;
import com.tsystems.logistics.logistics_vp.service.interfaces.AuthenticationInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@Log4j2
public class AuthenticationInfoController implements AuthenticationInfoApi {

    private final AuthenticationInfoService authenticationInfoService;

    @Override
    public ResponseEntity<AuthenticationInfoDto> authenticationInfoCreate(AuthenticationInfoDto authenticationInfoDto) {
        log.info("Start to register new authentication info");
        AuthenticationInfoDto resultAuthenticationInfoDto = authenticationInfoService
                .authenticationInfoCreate(authenticationInfoDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resultAuthenticationInfoDto);
    }

    @Override
    public ResponseEntity<Void> authenticationInfoDelete(Integer id) {
        log.info("Start to delete authentication info");
        authenticationInfoService.authenticationInfoDelete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Override
    public ResponseEntity<List<AuthenticationInfoDto>> authenticationInfoFindAll() {
        log.info("Start to find all authentication info");
        List<AuthenticationInfoDto> allResultAuthenticationInfoDtos = authenticationInfoService.authenticationInfoFindAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allResultAuthenticationInfoDtos);
    }

    @Override
    public ResponseEntity<AuthenticationInfoDto> authenticationInfoFindById(Integer id) {
        log.info("Start to find authentication info by id");
        AuthenticationInfoDto resultAuthenticationInfoDto = authenticationInfoService.authenticationInfoFindById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultAuthenticationInfoDto);
    }

    @Override
    public ResponseEntity<AuthenticationInfoDto> authenticationInfoFindByUsername(String username) {
        log.info("Start to find authentication info by username");
        AuthenticationInfoDto resultAuthenticationInfoDto = authenticationInfoService.authenticationInfoFindByUsername(username);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultAuthenticationInfoDto);
    }

//    @Override
//    public ResponseEntity<String> authenticationInfoSend(AuthenticationInfoToSendDto authenticationInfoToSendDto, String authToken) {
//        log.info("Start authorization (to send username and password)");
//        String username = authenticationInfoService.authenticationInfoSend(authenticationInfoToSendDto);
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(username);
//    }

    @Override
    public ResponseEntity<AuthenticationInfoDto> authenticationInfoUpdate(Integer id, AuthenticationInfoDto authenticationInfoDto) {
        log.info("Start to update authentication info");
        AuthenticationInfoDto resultAuthenticationInfoDto = authenticationInfoService
                .authenticationInfoUpdate(id, authenticationInfoDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(resultAuthenticationInfoDto);
    }
}
