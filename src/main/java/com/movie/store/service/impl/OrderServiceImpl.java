package com.movie.store.service.impl;

import com.movie.store.dao.OrderDao;
import com.movie.store.lib.Inject;
import com.movie.store.lib.Service;
import com.movie.store.model.Order;
import com.movie.store.model.Ticket;
import com.movie.store.model.User;
import com.movie.store.service.OrderService;
import com.movie.store.service.ShoppingCartService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(Set<Ticket> tickets, User user) {
        shoppingCartService.clear(shoppingCartService.getByUser(user));
        Order order = new Order();
        order.setDateOfCreation(LocalDateTime.now());
        order.getTickets().addAll(tickets);
        order.setUser(user);
        order = orderDao.add(order);
        return order;
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getByUser(user);
    }
}
