package org.psu.auth.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDto {
    private String login;
    private Collection<? extends GrantedAuthority> authorities;
    private String email;
    private int phoneCode;
    private long phone;
    private String firstName;
    private String lastName;
    private String patronym;
}
