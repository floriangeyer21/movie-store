package com.movie.store.controllers;

import com.movie.store.model.dto.UserRequestDto;
import com.movie.store.model.dto.UserResponseDto;
import com.movie.store.security.AuthenticationService;
import com.movie.store.service.interfaces.UserService;
import com.movie.store.service.mappers.UserMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j
@RestController
@RequestMapping("/user")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    @Autowired
    public AuthenticationController(UserService userService,
                                    AuthenticationService authenticationService,
                                    UserMapper userMapper) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody UserRequestDto userRequestDto) {
        log.info("Calling method register in AuthenticationController, "
                + userRequestDto);
        String email = userRequestDto.getEmail();
        String password = userRequestDto.getPassword();
        return userMapper.mapUserToResponseDto(
                authenticationService.register(email, password));
    }
}
