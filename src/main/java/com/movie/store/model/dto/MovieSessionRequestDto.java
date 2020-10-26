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
public class MovieSessionRequestDto {
    private LocalDateTime showTime;
    private String movieId;
    private String cinemaHallId;
}
