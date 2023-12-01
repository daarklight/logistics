package com.tsystems.logistics.logistics_vp.service;

import com.tsystems.logistics.logistics_vp.code.model.AuthenticationInfoDto;
import com.tsystems.logistics.logistics_vp.code.model.AuthenticationInfoToSendDto;
import com.tsystems.logistics.logistics_vp.entity.AuthenticationInfo;
import com.tsystems.logistics.logistics_vp.mapper.AuthenticationInfoMapper;
import com.tsystems.logistics.logistics_vp.repository.AuthenticationInfoRepository;
import com.tsystems.logistics.logistics_vp.service.interfaces.AuthenticationInfoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationInfoServiceImpl implements AuthenticationInfoService {

    private final AuthenticationInfoRepository authenticationInfoRepository;

    @Override
    public List<AuthenticationInfoDto> authenticationInfoFindAll() {
        return authenticationInfoRepository.findAll().stream().map(authenticationInfo ->
                authenticationInfoDto(authenticationInfo)).toList();
    }

    @Override
    public AuthenticationInfoDto authenticationInfoCreate(AuthenticationInfoDto authenticationInfoDto) {
        AuthenticationInfo authenticationInfo = AuthenticationInfo.builder()
                .id(authenticationInfoDto.getId())
                .username(authenticationInfoDto.getUsername())
                .password(authenticationInfoDto.getPassword())
                .build();
        authenticationInfoRepository.save(authenticationInfo);
        return authenticationInfoDto(authenticationInfo);
    }

    @Override
    public AuthenticationInfoDto authenticationInfoUpdate(Integer id, AuthenticationInfoDto authenticationInfoDto) {
        AuthenticationInfo authenticationInfo = authenticationInfoRepository.findById(id).orElseThrow();
        authenticationInfo.setUsername(authenticationInfoDto.getUsername());
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
    public AuthenticationInfoDto authenticationInfoFindByUsername(String login) {
        AuthenticationInfo authenticationInfo = authenticationInfoRepository.findByUsername(login);
        return authenticationInfoDto(authenticationInfo);
    }

    @Override
    public String authenticationInfoSend(AuthenticationInfoToSendDto authenticationInfoToSendDto) {
        return authenticationInfoToSendDto.getUsername();
    }

    private AuthenticationInfoDto authenticationInfoDto(AuthenticationInfo authenticationInfo) {
        return AuthenticationInfoMapper.INSTANCE.authenticationInfoToAuthenticationInfoDto(authenticationInfo);
    }
}
