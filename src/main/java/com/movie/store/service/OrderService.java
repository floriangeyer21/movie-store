package com.movie.store.service;

import com.movie.store.model.Order;
import com.movie.store.model.Ticket;
import com.movie.store.model.User;
import java.util.List;
import java.util.Set;

public interface OrderService {
    Order completeOrder(Set<Ticket> tickets, User user);

    List<Order> getOrderHistory(User user);
}
