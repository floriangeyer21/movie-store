package com.movie.store.service.interfaces;

import com.movie.store.model.Role;

public interface RoleService {
    void add(Role role);

    Role getRoleByName(String roleName);
}
