package com.movie.store.service.impl;

import com.movie.store.dao.ShoppingCartDao;
import com.movie.store.dao.TicketDao;
import com.movie.store.lib.Inject;
import com.movie.store.lib.Service;
import com.movie.store.model.MovieSession;
import com.movie.store.model.ShoppingCart;
import com.movie.store.model.Ticket;
import com.movie.store.model.User;
import com.movie.store.service.ShoppingCartService;
import java.util.ArrayList;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;
    @Inject
    private TicketDao ticketDao;

    @Override
    public ShoppingCart addSession(MovieSession movieSession, User user) {
        log.info("Calling method addSession() in ShoppingCartService");
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setMovieSession(movieSession);
        ticketDao.add(ticket);
        ShoppingCart shoppingCart = shoppingCartDao.getByUser(user);
        shoppingCart.getTickets().add(ticket);
        shoppingCartDao.update(shoppingCart);
        return shoppingCart;
    }

    @Override
    public ShoppingCart getByUser(User user) {
        log.info("Calling method getByUser() in ShoppingCartService");
        return shoppingCartDao.getByUser(user);
    }

    @Override
    public ShoppingCart registerNewShoppingCart(User user) {
        log.info("Calling method registerNewShoppingCart() in ShoppingCartService");
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        return shoppingCartDao.add(shoppingCart);
    }

    @Override
    public ShoppingCart clear(ShoppingCart shoppingCart) {
        log.info("Calling method clear() in ShoppingCartService");
         shoppingCart.getTickets().clear();
        //  Case with deleting tickets from orders_tickets and shopping_-crts_tickets
       // shoppingCart.setTickets(new ArrayList<>());
        shoppingCartDao.update(shoppingCart);
        return shoppingCart;
    }
}
