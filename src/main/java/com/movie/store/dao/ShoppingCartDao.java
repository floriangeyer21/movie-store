package com.movie.store.dao;

import com.movie.store.model.ShoppingCart;
import com.movie.store.model.User;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);
}
