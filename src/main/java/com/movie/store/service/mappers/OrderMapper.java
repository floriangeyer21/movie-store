package com.movie.store.service.mappers;

import com.movie.store.model.Order;
import com.movie.store.model.dto.OrderResponseDto;
import com.movie.store.model.dto.TicketResponseDto;
import java.util.Set;
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
                .tickets(getTicketResponseDtos(order))
                .userId(order.getUser().getId())
                .dateOfCreation(order.getDateOfCreation()).build();
    }

    private Set<TicketResponseDto> getTicketResponseDtos(Order order) {
        return order.getTickets().stream()
                .map(ticketMapper::mapTicketToResponseDto)
                .collect(Collectors.toSet());
    }
}
