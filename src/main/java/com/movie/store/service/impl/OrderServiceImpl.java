package com.movie.store.service.impl;

import com.movie.store.dao.OrderDao;
import com.movie.store.model.Order;
import com.movie.store.model.Ticket;
import com.movie.store.model.User;
import com.movie.store.service.OrderService;
import com.movie.store.service.ShoppingCartService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, ShoppingCartService shoppingCartService) {
        this.orderDao = orderDao;
        this.shoppingCartService = shoppingCartService;
    }

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
