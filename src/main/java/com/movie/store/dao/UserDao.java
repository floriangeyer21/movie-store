package com.movie.store.dao;

import com.movie.store.model.User;

public interface UserDao {
    User add(User user);

    User findByEmail(String email);
}
