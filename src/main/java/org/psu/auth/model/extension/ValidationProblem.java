package org.psu.auth.model.extension;

public final class ValidationProblem extends AbstractProblem {

    private static final long serialVersionUID = 8220652723975480113L;
    private static final String TYPE = "validationProblem";
    private static final String CODE = "51";

    private ValidationProblem() {
        super(TYPE, CODE, "", "");
    }

    private ValidationProblem(String title) {
        super(TYPE, CODE, title, "");
    }

    private ValidationProblem(String title, String detail) {
        super(TYPE, CODE, title, detail);
    }

    public static ValidationProblem of(String title) {
        return new ValidationProblem(title);
    }

    public static ValidationProblem of(String title, String detail) {
        return new ValidationProblem(title, detail);
    }
}