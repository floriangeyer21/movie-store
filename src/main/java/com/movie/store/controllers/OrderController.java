package com.movie.store.controllers;

import com.movie.store.model.Ticket;
import com.movie.store.model.User;
import com.movie.store.model.dto.OrderResponseDto;
import com.movie.store.service.interfaces.OrderService;
import com.movie.store.service.interfaces.ShoppingCartService;
import com.movie.store.service.interfaces.UserService;
import com.movie.store.service.mappers.OrderMapper;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(UserService userService,
                           ShoppingCartService shoppingCartService,
                           OrderService orderService,
                           OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping
    public void completeOrder(@RequestBody Long userId) {
        User user = userService.findById(userId).get();
        Set<Ticket> tickets = shoppingCartService.getByUser(user).getTickets();
        orderService.completeOrder(tickets, user);
    }

    @GetMapping
    public List<OrderResponseDto> getAllOrdersByUserId(@RequestParam("userId") Long id) {
        return orderMapper.mapAllOrderToResponseDto(
                orderService.getOrderHistory(userService.findById(id).get()));
    }
}
