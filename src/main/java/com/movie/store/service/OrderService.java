package com.movie.store.service;

import com.movie.store.model.Order;
import com.movie.store.model.Ticket;
import com.movie.store.model.User;
import java.util.List;

public interface OrderService {
    Order completeOrder(List<Ticket> tickets, User user);

    List<Order> getOrderHistory(User user);
}
