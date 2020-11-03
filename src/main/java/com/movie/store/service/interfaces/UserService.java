package com.movie.store.service.interfaces;

import com.movie.store.model.User;

public interface UserService {
    User add(User user);

    User findByEmail(String email);

    User findById(Long id);
}
