package com.tsystems.logistics.logistics_vp.service;

import com.tsystems.logistics.logistics_vp.code.model.AuthenticationInfoDto;
import com.tsystems.logistics.logistics_vp.entity.AuthenticationInfo;
import com.tsystems.logistics.logistics_vp.enums.Role;
import com.tsystems.logistics.logistics_vp.exceptions.custom.NoSuchAuthenticationInfoException;
import com.tsystems.logistics.logistics_vp.mapper.AuthenticationInfoMapper;
import com.tsystems.logistics.logistics_vp.repository.AuthenticationInfoRepository;
import com.tsystems.logistics.logistics_vp.service.interfaces.AuthenticationInfoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
                .role(Role.valueOf(authenticationInfoDto.getRole().toString()))
                .username(authenticationInfoDto.getUsername())
                .password(new BCryptPasswordEncoder().encode(authenticationInfoDto.getPassword()))
                .build();
        authenticationInfoRepository.save(authenticationInfo);
        return authenticationInfoDto(authenticationInfo);
    }

    @Override
    public AuthenticationInfoDto authenticationInfoUpdate(Integer id, AuthenticationInfoDto authenticationInfoDto) {
        AuthenticationInfo authenticationInfo = getAuthenticationInfoFromDb(id);
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
        return authenticationInfoDto(getAuthenticationInfoFromDb(id));
    }

    @Override
    public AuthenticationInfoDto authenticationInfoFindByUsername(String login) {
        AuthenticationInfo authenticationInfo = authenticationInfoRepository.findByUsername(login);
        return authenticationInfoDto(authenticationInfo);
    }

//    @Override
//    public String authenticationInfoSend(AuthenticationInfoToSendDto authenticationInfoToSendDto) {
//        return authenticationInfoToSendDto.getUsername();
//    }

    @Override
    public AuthenticationInfo getAuthenticationInfoFromDb(Integer id) {
        return authenticationInfoRepository.findById(id).orElseThrow(() ->
                new NoSuchAuthenticationInfoException("This authentication info does not exist in database"));
    }

    private AuthenticationInfoDto authenticationInfoDto(AuthenticationInfo authenticationInfo) {
        return AuthenticationInfoMapper.INSTANCE.authenticationInfoToAuthenticationInfoDto(authenticationInfo);
    }
}
