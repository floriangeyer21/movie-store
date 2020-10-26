package com.movie.store.service.mappers;

import com.movie.store.model.Order;
import com.movie.store.model.dto.OrderResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final TicketMapper ticketMapper;

    @Autowired
    public OrderMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public OrderResponseDto mapOrderToResponseDto(Order order) {
        return OrderResponseDto.builder()
                .id(order.getId())
                .tickets(ticketMapper.mapAllTicketToResponseDto(order.getTickets()))
                .userId(order.getUser().getId())
                .dateOfCreation(order.getDateOfCreation()).build();
    }

    public List<OrderResponseDto> mapAllOrderToResponseDto(List<Order> orders) {
        return orders.stream()
                .map(this::mapOrderToResponseDto)
                .collect(Collectors.toList());
    }
}
