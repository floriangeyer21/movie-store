package com.movie.store.service.impl;

import com.movie.store.dao.UserDao;
import com.movie.store.lib.Inject;
import com.movie.store.lib.Service;
import com.movie.store.model.User;
import com.movie.store.service.UserService;
import com.movie.store.util.HashUtil;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        user.setSalt(HashUtil.getSalt());
        user.setPassword(HashUtil.hashPassword(user.getPassword(), user.getSalt()));
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
