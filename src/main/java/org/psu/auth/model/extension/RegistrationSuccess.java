package org.psu.auth.model.extension;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(staticName="of")
@Getter
@Setter
public class RegistrationSuccess {
    private String header;
    private String text;
}
