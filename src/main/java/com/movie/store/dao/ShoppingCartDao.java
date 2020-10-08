package com.movie.store.dao;

import com.movie.store.model.ShoppingCart;
import com.movie.store.model.User;
import java.util.Optional;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart getByUser(User user);

    ShoppingCart update(ShoppingCart shoppingCart);
}
