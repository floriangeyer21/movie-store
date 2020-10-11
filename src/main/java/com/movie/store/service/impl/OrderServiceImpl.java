package com.movie.store.service.impl;

import com.movie.store.dao.OrderDao;
import com.movie.store.dao.ShoppingCartDao;
import com.movie.store.lib.Inject;
import com.movie.store.lib.Service;
import com.movie.store.model.Order;
import com.movie.store.model.Ticket;
import com.movie.store.model.User;
import com.movie.store.service.OrderService;
import com.movie.store.service.ShoppingCartService;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartService shoppingCartService;
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        Order order = new Order();
        order.setDateOfCreation(LocalDateTime.now());
        order.setTickets(tickets);
        order.setUser(user);
        shoppingCartService.clear(shoppingCartDao.getByUser(user));
        return orderDao.add(order);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getByUser(user);
    }
}
