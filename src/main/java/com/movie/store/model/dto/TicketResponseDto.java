package com.movie.store.model.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDto {
    private Long id;
    private LocalDateTime showTime;
    private int cinemaHallCapacity;
    private String movieTitle;
}
