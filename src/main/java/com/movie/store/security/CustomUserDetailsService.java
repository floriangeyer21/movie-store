package com.movie.store.security;

import com.movie.store.dao.interfaces.UserDao;
import com.movie.store.model.User;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserDao userDao;

    @Autowired
    public CustomUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userDao.findByEmail(email);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException(
                "User with email " + email + "not found."));
        UserBuilder builder = org.springframework.security.core.userdetails
                .User.withUsername(email);
        builder.password(user.getPassword());
        String[] roles = user.getRoles().stream()
                .map(role -> role.getRoleName().toString())
                .toArray(String[]::new);
        builder.roles(roles);
        return builder.build();
    }
}
