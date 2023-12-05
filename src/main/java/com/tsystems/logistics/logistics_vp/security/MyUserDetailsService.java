package com.tsystems.logistics.logistics_vp.security;

import com.tsystems.logistics.logistics_vp.entity.AuthenticationInfo;
import com.tsystems.logistics.logistics_vp.repository.AuthenticationInfoRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final AuthenticationInfoRepository authenticationInfoRepository;

    public MyUserDetailsService(AuthenticationInfoRepository authenticationInfoRepository) {
        this.authenticationInfoRepository = authenticationInfoRepository;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthenticationInfo user = authenticationInfoRepository.findByUsername(username);
        if (user==null) {
            throw new UsernameNotFoundException("User could not be found");
        }

        List<SimpleGrantedAuthority> roles = Arrays.asList(
                new SimpleGrantedAuthority(user.getRole().toString()));
        return new User(username, user.getPassword(), roles);
    }
}
