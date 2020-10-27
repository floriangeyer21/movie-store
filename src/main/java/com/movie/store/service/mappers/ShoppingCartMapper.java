package com.movie.store.service.mappers;

import com.movie.store.model.ShoppingCart;
import com.movie.store.model.dto.ShoppingCartResponseDto;
import com.movie.store.model.dto.TicketResponseDto;
import java.util.Set;
import java.util.stream.Collectors;
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
                .tickets(getTicketResponseDtos(shoppingCart))
                .build();
    }

    private Set<TicketResponseDto> getTicketResponseDtos(ShoppingCart shoppingCart) {
        return shoppingCart.getTickets().stream()
                .map(ticketMapper::mapTicketToResponseDto)
                .collect(Collectors.toSet());
    }
}
