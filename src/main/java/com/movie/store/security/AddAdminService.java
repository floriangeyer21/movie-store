package com.movie.store.security;

import com.movie.store.model.Role;
import com.movie.store.model.User;
import com.movie.store.service.interfaces.RoleService;
import com.movie.store.service.interfaces.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddAdminService {
    private static final String USER = "USER";
    private static final String ADMIN = "ADMIN";
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AddAdminService(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void initData() {
        roleService.add(Role.of(USER));
        roleService.add(Role.of(ADMIN));
        User admin = User.builder()
                .email("admin@gmail.com")
                .password("12345")
                .roles(Set.of(roleService.getRoleByName(ADMIN)))
                .build();
        userService.add(admin);
    }
}
