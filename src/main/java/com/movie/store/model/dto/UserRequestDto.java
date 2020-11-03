package com.movie.store.model.dto;

import com.movie.store.validation.EmailConstraint;
import com.movie.store.validation.PasswordConstraint;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@PasswordConstraint
public class UserRequestDto {
    @Size(min = 4)
    @EmailConstraint
    private String email;
    private String password;
    private String repeatPassword;
}
