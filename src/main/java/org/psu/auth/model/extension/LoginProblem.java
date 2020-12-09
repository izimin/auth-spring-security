package org.psu.auth.model.extension;

import lombok.Getter;
import lombok.Setter;

public class LoginProblem extends AbstractProblem {

    private static final long serialVersionUID = 8220652723975480212L;
    private static final String TYPE = "loginProblem";
    private static final String CODE = "212";

    private LoginProblem() {
        super(TYPE, CODE, "", "");
    }

    private LoginProblem(String title) {
        super(TYPE, CODE, title, "");
    }

    private LoginProblem(String title, String detail) {
        super(TYPE, CODE, title, detail);
    }

    public static LoginProblem of(String title) {
        return new LoginProblem(title);
    }

    public static LoginProblem of(String title, String detail) {
        return new LoginProblem(title, detail);
    }
}
