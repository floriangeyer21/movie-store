package com.movie.store.service;

import com.movie.store.model.MovieSession;
import com.movie.store.model.ShoppingCart;
import com.movie.store.model.User;

public interface ShoppingCartService {
    ShoppingCart addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    ShoppingCart registerNewShoppingCart(User user);

    ShoppingCart clear(ShoppingCart shoppingCart);
}
