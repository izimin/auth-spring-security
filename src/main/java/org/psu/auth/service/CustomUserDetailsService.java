package org.psu.auth.service;

import org.psu.auth.model.pojo.UserPojo;
import org.psu.auth.repository.UserRepository;
import org.psu.security.model.*;
import org.psu.security.model.System;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPojo user = userRepository.findByLogin(username);

        return UserDetailsPsu
                .builder()
                .password(user.getHash())
                .login(user.getLogin())
                .authorities(
                        user.getRoles().stream().map(auth ->
                                new SimpleGrantedAuthority(auth.getRoleName().name())).collect(Collectors.toList()))
                .email(user.getEmail())
                .phoneCode(
                        PhoneCode
                                .builder()
                                .id(user.getPhoneCodeId().getPhoneCodeId())
                                .value(user.getPhoneCodeId().getPhoneCodeValue())
                                .system(
                                        System
                                                .builder()
                                                .id(user.getPhoneCodeId().getSystemId().getSystemId())
                                                .name(user.getPhoneCodeId().getSystemId().getSystemName())
                                                .build()
                                ).build()
                )
                .phone(user.getPhone())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .patronym(user.getPatronym())
                .build();
    }
}
