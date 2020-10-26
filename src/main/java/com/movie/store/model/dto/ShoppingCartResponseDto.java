package com.movie.store.model.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartResponseDto {
    private Long id;
    private Long userId;
    private Set<TicketResponseDto> tickets;
}
