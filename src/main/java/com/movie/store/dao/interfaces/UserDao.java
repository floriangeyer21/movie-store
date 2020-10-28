package com.movie.store.dao.interfaces;

import com.movie.store.model.User;
import java.util.Optional;

public interface UserDao {
    User add(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);
}
