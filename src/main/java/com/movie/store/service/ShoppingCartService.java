package com.movie.store.service;

import com.movie.store.model.MovieSession;
import com.movie.store.model.ShoppingCart;
import com.movie.store.model.User;

public interface ShoppingCartService {
    /**
     * This method is responsible to add a Ticket to the ShoppingCart
     * @param movieSession Contains the information required for a ticket
     * @param user - user who wan't to buy a ticket for a specific MovieSession
     */
    ShoppingCart addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    ShoppingCart registerNewShoppingCart(User user);

    ShoppingCart clear(ShoppingCart shoppingCart);
}
