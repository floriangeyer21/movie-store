package com.movie.store.security;

import com.movie.store.exceptions.AuthenticationException;
import com.movie.store.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password);
}
