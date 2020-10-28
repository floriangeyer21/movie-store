package com.movie.store.service.mappers;

import com.movie.store.model.Ticket;
import com.movie.store.model.dto.TicketResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public TicketResponseDto mapTicketToResponseDto(Ticket ticket) {
        return TicketResponseDto.builder()
                .id(ticket.getId())
                .cinemaHallCapacity(ticket.getMovieSession().getCinemaHall().getCapacity())
                .showTime(ticket.getMovieSession().getShowTime())
                .movieTitle(ticket.getMovieSession().getMovie().getTitle()).build();
    }
}
