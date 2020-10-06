package com.movie.store.service;

import com.movie.store.model.User;

public interface UserService {
    User add(User user);

    User findByEmail(String email);
}
