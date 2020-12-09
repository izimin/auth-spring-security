package org.psu.auth.model.extension;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public abstract class AbstractProblem implements Serializable {
    private static final long serialVersionUID = 5862597885191130386L;
    private final String type;
    private final String code;
    private String title;
    private String detail;
}