package com.movie.store.dao;

import com.movie.store.model.Order;
import com.movie.store.model.User;
import java.util.List;

public interface OrderDao {
    Order add(Order order);

    List<Order> getByUser(User user);
}
