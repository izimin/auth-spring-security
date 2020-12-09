package org.psu.auth.service;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.psu.auth.model.dto.UserRegistrationDto;
import org.psu.auth.model.pojo.UserPojo;

@Mapper(componentModel = "spring",
        uses = UserRegistrationDto.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RegistrationUserMapper {
    @Mapping(target = "hash", source = "registration.password1")
    UserPojo registrationToUser(UserRegistrationDto registration);
}