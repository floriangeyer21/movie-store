package com.movie.store.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements
        ConstraintValidator<EmailConstraint, String> {
    private static final String EMAIL_REGEX = "^(.+)@(.+)$";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext cxt) {
        return email != null && email.matches(EMAIL_REGEX);
    }
}
