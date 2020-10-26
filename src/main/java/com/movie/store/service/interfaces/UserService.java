package com.movie.store.service.interfaces;

import com.movie.store.model.User;
import java.util.Optional;

public interface UserService {
    User add(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);
}
