package com.movie.store.dao.interfaces;

import com.movie.store.model.Role;

public interface RoleDao {
    void add(Role role);

    Role getRoleByName(String roleName);
}
