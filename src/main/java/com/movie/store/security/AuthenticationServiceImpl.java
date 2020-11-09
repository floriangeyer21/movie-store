package com.movie.store.security;

import com.movie.store.model.User;
import com.movie.store.service.interfaces.RoleService;
import com.movie.store.service.interfaces.ShoppingCartService;
import com.movie.store.service.interfaces.UserService;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final RoleService roleService;

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService,
                                     RoleService roleService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.roleService = roleService;
    }

    @Override
    public User register(String email, String password) {
        User user = new User(email, password);
        user.setRoles(Set.of(roleService.getRoleByName("USER")));
        user = userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
