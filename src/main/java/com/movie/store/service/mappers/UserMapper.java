package com.movie.store.service.mappers;

import com.movie.store.model.User;
import com.movie.store.model.dto.UserResponseDto;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

@Log4j
@Component
public class UserMapper {

    public UserResponseDto mapUserToResponseDto(User user) {
        log.info("Calling method mapUserToResponseDto in UserMapper, " + user);
        return UserResponseDto.builder().email(user.getEmail())
                .id(user.getId()).build();
    }
}
