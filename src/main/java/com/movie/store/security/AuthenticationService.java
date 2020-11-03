package com.movie.store.security;

import com.movie.store.model.User;

public interface AuthenticationService {

    User register(String email, String password);
}
