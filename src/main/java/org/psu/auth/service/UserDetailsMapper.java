package org.psu.auth.service;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.psu.auth.model.dto.UserDetailsDto;
import org.psu.security.model.UserDetailsPsu;

@Mapper(componentModel = "spring",
        uses = UserDetailsPsu.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserDetailsMapper {
    @Mapping(target = "phoneCode", source = "details.phoneCode.value")
    UserDetailsDto userDetailsToDto(UserDetailsPsu details);
}
