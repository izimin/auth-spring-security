package org.psu.auth.model.extension;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(staticName="of")
@Getter
@Setter
public class Result<T> {
    private T data;
}
