package org.psu.auth.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BaseSecurityException extends Exception {
    public BaseSecurityException(String message){
        super(message);
    }
}
