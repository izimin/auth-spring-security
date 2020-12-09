package org.psu.auth.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationDto {
    private String login;
    private String firstName;
    private String lastName;
    private String patronym;
    private String email;
    private String phone;
    private String password1;
    private String password2;
}
