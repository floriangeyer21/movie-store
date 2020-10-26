package com.movie.store.service.mappers;

import com.movie.store.model.ShoppingCart;
import com.movie.store.model.dto.ShoppingCartResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    private final TicketMapper ticketMapper;

    public ShoppingCartMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public ShoppingCartResponseDto mapShoppingCartToResponseDto(ShoppingCart shoppingCart) {
        return ShoppingCartResponseDto.builder()
                .id(shoppingCart.getId())
                .tickets(ticketMapper.mapAllTicketToResponseDto(shoppingCart.getTickets()))
                .userId(shoppingCart.getUser().getId()).build();
    }
}
