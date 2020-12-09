package org.psu.auth.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
public class ValidationException extends BaseSecurityException {
    public ValidationException(String message) {
        super(message);
    }
}
