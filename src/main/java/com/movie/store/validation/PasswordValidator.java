package com.movie.store.validation;

import com.movie.store.model.dto.UserRequestDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements
        ConstraintValidator<PasswordConstraint, UserRequestDto> {

    @Override
    public boolean isValid(UserRequestDto userRequestDto,
                           ConstraintValidatorContext cxt) {
        String password = userRequestDto.getPassword();
        String repeatPassword = userRequestDto.getRepeatPassword();
        return password != null && password.equals(repeatPassword);
    }
}
