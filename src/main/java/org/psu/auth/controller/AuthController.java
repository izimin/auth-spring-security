package org.psu.auth.controller;

import org.psu.auth.exceptions.ValidationException;
import org.psu.auth.model.dto.UserDetailsDto;
import org.psu.auth.model.extension.*;
import org.psu.auth.model.dto.UserLoginDto;
import org.psu.auth.model.dto.UserRegistrationDto;
import org.psu.auth.service.AuthService;
import org.psu.auth.service.RegistrationUserMapper;
import org.psu.auth.service.UserDetailsMapper;
import org.psu.auth.service.ValidationService;
import org.psu.security.model.UserDetailsPsu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class AuthController {
    private final AuthService authService;
    private final ValidationService validationService;
    private final RegistrationUserMapper mapper;
    private final UserDetailsMapper udMapper;

    @Autowired
    public AuthController(AuthService authService, ValidationService validationService,
                          RegistrationUserMapper mapper, UserDetailsMapper udMapper) {
        this.authService = authService;
        this.validationService = validationService;
        this.mapper = mapper;
        this.udMapper = udMapper;
    }

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(){
        UserDetailsPsu details = authService.getUserDetails();
        if (details != null) {
            Map<String, Result<UserDetailsDto>> result = new HashMap<>();
            result.put("details", Result.of(udMapper.userDetailsToDto(details)));
            return ResponseEntity.ok(Base.of("200", result,null));
        }
        return new ResponseEntity<>(
                Base.of("401", null, InfoProblem.of("Ошибка получения данных",
                "Произошла ошибка получения данных пользователя. Проверьте, залогинен ли пользователь")),
                HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> addUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        try{
            validationService.validateUserData(userRegistrationDto);
        }
        catch (ValidationException e){
            return new ResponseEntity<>(
                    Base.of("406", null,
                    ValidationProblem.of("Ошибка валидации", e.getMessage())), HttpStatus.NOT_ACCEPTABLE);
        }
        authService.addUser(mapper.registrationToUser(userRegistrationDto));
        Map<String, Result<RegistrationSuccess>> results = new HashMap<>();
        results.put("info",
                Result.of(RegistrationSuccess.of(
                "Registration success",
                "Registration was successful")));
        return new ResponseEntity<>(
                Base.of("201", results, null), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto loginRequest) {
        try {
            authService.login(loginRequest);
            Map<String, Result<UserDetailsDto>> result = new HashMap<>();
            result.put("details", Result.of(udMapper.userDetailsToDto(authService.getUserDetails())));
            return ResponseEntity.ok(Base.of("200", result, null));
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(
                    Base.of("401",null,
                    LoginProblem.of("Ошибка входа", e.getMessage())
                    ), HttpStatus.UNAUTHORIZED);
        }
    }
}
