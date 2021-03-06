package com.movie.store.service.impl;

import com.movie.store.dao.interfaces.ShoppingCartDao;
import com.movie.store.dao.interfaces.TicketDao;
import com.movie.store.model.MovieSession;
import com.movie.store.model.ShoppingCart;
import com.movie.store.model.Ticket;
import com.movie.store.model.User;
import com.movie.store.service.interfaces.ShoppingCartService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartDao shoppingCartDao;
    private final TicketDao ticketDao;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao, TicketDao ticketDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.ticketDao = ticketDao;
    }

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
        shoppingCartDao.update(shoppingCart);
        return shoppingCart;
    }
}
