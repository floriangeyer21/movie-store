package com.movie.store.controllers;

import com.movie.store.model.User;
import com.movie.store.model.dto.UserResponseDto;
import com.movie.store.service.interfaces.UserService;
import com.movie.store.service.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService,
                          UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/by-email")
    public UserResponseDto getUserByEmail(Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        return userMapper.mapUserToResponseDto(user);
    }
}
