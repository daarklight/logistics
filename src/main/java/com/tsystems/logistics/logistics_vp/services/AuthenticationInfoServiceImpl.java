package com.tsystems.logistics.logistics_vp.services;

import com.tsystems.logistics.logistics_vp.code.model.AuthenticationInfoDto;
import com.tsystems.logistics.logistics_vp.entities.AuthenticationInfo;
import com.tsystems.logistics.logistics_vp.mappers.AuthenticationInfoMapper;
import com.tsystems.logistics.logistics_vp.repositories.AuthenticationInfoRepository;
import com.tsystems.logistics.logistics_vp.repositories.customized.CustomizedAuthenticationInfoRepository;
import com.tsystems.logistics.logistics_vp.services.interfaces.AuthenticationInfoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationInfoServiceImpl implements AuthenticationInfoService {

    private final AuthenticationInfoRepository authenticationInfoRepository;
    private final CustomizedAuthenticationInfoRepository customizedAuthenticationInfoRepository;

    @Override
    public List<AuthenticationInfoDto> authenticationInfoFindAll() {
        return authenticationInfoRepository.findAll().stream().map(authenticationInfo ->
                authenticationInfoDto(authenticationInfo)).toList();
    }

    @Override
    public AuthenticationInfoDto authenticationInfoCreate(AuthenticationInfoDto authenticationInfoDto) {
        AuthenticationInfo authenticationInfo = AuthenticationInfo.builder()
                .id(authenticationInfoDto.getId())
                .login(authenticationInfoDto.getLogin())
                .password(authenticationInfoDto.getPassword())
                .build();
        authenticationInfoRepository.save(authenticationInfo);
        return authenticationInfoDto(authenticationInfo);
    }

    @Override
    public AuthenticationInfoDto authenticationInfoUpdate(Integer id, AuthenticationInfoDto authenticationInfoDto) {
        AuthenticationInfo authenticationInfo = authenticationInfoRepository.findById(id).orElseThrow();
        authenticationInfo.setLogin(authenticationInfoDto.getLogin());
        authenticationInfo.setPassword(authenticationInfoDto.getPassword());
        authenticationInfoRepository.save(authenticationInfo);
        return authenticationInfoDto(authenticationInfo);
    }

    @Override
    public void authenticationInfoDelete(Integer id) {
        authenticationInfoRepository.deleteById(id);
    }

    @Override
    public AuthenticationInfoDto authenticationInfoFindById(Integer id) {
        AuthenticationInfo authenticationInfo = authenticationInfoRepository.findById(id).orElseThrow();
        return authenticationInfoDto(authenticationInfo);
    }

    @Override
    public AuthenticationInfoDto authenticationInfoFindByLogin(String login) {
        AuthenticationInfo authenticationInfo = customizedAuthenticationInfoRepository.findByLogin(login);
        return authenticationInfoDto(authenticationInfo);
    }

    private AuthenticationInfoDto authenticationInfoDto(AuthenticationInfo authenticationInfo) {
        return AuthenticationInfoMapper.INSTANCE.authenticationInfoToAuthenticationInfoDto(authenticationInfo);
    }
}
