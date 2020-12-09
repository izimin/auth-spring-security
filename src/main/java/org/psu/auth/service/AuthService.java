package org.psu.auth.service;

import org.psu.auth.model.dto.UserLoginDto;
import org.psu.auth.model.enums.AlgorithmEnum;
import org.psu.auth.model.enums.RoleEnum;
import org.psu.auth.model.pojo.UserPojo;
import org.psu.auth.repository.*;
import org.psu.security.model.UserDetailsPsu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.regex.Pattern;

@Service
public class AuthService {
    private final AlgorithmRepository algorithmRepository;
    private final PhoneCodeRepository phoneCodeRepository;
    private final RoleRepository roleRepository;
    private final SystemRepository systemRepository;
    private final UserRepository userRepository;
    private final AuthenticationProvider provider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthService(AlgorithmRepository algorithmRepository,
                       PhoneCodeRepository phoneCodeRepository,
                       RoleRepository roleRepository,
                       SystemRepository systemRepository,
                       UserRepository userRepository,
                       AuthenticationProvider provider,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.algorithmRepository = algorithmRepository;
        this.phoneCodeRepository = phoneCodeRepository;
        this.roleRepository = roleRepository;
        this.systemRepository = systemRepository;
        this.userRepository = userRepository;
        this.provider = provider;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void addUser(UserPojo user) {
        user.setPhoneCodeId(phoneCodeRepository.findByPhoneCodeValue(7));
        user.setSystemId(systemRepository.findBySystemName("Test system"));
        user.setAlgorithmId(algorithmRepository.findByAlgorithmName(AlgorithmEnum.BCrypt));
        user.setHash(bCryptPasswordEncoder.encode(user.getHash()));
        String[] hashedThings = user.getHash().split(Pattern.quote("$"));
        user.setSalt(String.format("$%s$%s$%s", hashedThings[1], hashedThings[2], hashedThings[3].substring(0, 22)));
        user.setRoles(new HashSet<>());
        user.getRoles().add(roleRepository.findByRoleName(RoleEnum.ROLE_USER));
        userRepository.save(user);
    }

    public void login(UserLoginDto loginInfo){
        Authentication authentication = provider.authenticate(
                new UsernamePasswordAuthenticationToken(loginInfo.getLogin(), loginInfo.getPassword())
        );
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    public UserDetailsPsu getUserDetails(){
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null)
            return null;
        Authentication authentication = context.getAuthentication();
        if (authentication == null)
            return null;
        if (!(authentication.getPrincipal() instanceof UserDetailsPsu))
            return null;
        return (UserDetailsPsu) authentication.getPrincipal();
    }
}
