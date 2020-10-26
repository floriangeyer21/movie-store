package com.movie.store.service.mappers;

import com.movie.store.model.Ticket;
import com.movie.store.model.dto.TicketResponseDto;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public TicketResponseDto mapTicketToResponseDto(Ticket ticket) {
        return TicketResponseDto.builder()
                .id(ticket.getId())
                .capacity(ticket.getMovieSession().getCinemaHall().getCapacity())
                .showTime(ticket.getMovieSession().getShowTime())
                .movieTitle(ticket.getMovieSession().getMovie().getTitle()).build();
    }

    public Set<TicketResponseDto> mapAllTicketToResponseDto(Set<Ticket> tickets) {
        return tickets.stream()
                .map(this::mapTicketToResponseDto)
                .collect(Collectors.toSet());
    }
}
