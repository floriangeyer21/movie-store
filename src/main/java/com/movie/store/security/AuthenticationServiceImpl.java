package com.movie.store.security;

import com.movie.store.exceptions.AuthenticationException;
import com.movie.store.lib.Inject;
import com.movie.store.lib.Service;
import com.movie.store.model.User;
import com.movie.store.service.UserService;
import com.movie.store.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> userFromDB = userService.findByEmail(email);
        if (userFromDB.isPresent() && userFromDB.get().getPassword().equals(
                HashUtil.hashPassword(password, userFromDB.get().getSalt()))) {
            return userFromDB.get();
        }
        throw new AuthenticationException("Incorrect users name or password");
    }

    @Override
    public User register(String email, String password) {
        User user = new User(email, password);
        user = userService.add(user);
        return user;
    }
}
