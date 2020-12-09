package org.psu.auth.model.extension;

public class InfoProblem extends AbstractProblem {
    private static final long serialVersionUID = 8220652723975480212L;
    private static final String TYPE = "loginProblem";
    private static final String CODE = "212";

    private InfoProblem() {
        super(TYPE, CODE, "", "");
    }

    private InfoProblem(String title) {
        super(TYPE, CODE, title, "");
    }

    private InfoProblem(String title, String detail) {
        super(TYPE, CODE, title, detail);
    }

    public static InfoProblem of(String title) {
        return new InfoProblem(title);
    }

    public static InfoProblem of(String title, String detail) {
        return new InfoProblem(title, detail);
    }
}
