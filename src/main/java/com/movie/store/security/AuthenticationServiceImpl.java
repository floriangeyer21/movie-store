package com.movie.store.security;

import com.movie.store.exceptions.AuthenticationException;
import com.movie.store.model.User;

public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public User login(String email, String password) throws AuthenticationException {
        return null;
    }

    @Override
    public User register(String email, String password) {
        return null;
    }
}
