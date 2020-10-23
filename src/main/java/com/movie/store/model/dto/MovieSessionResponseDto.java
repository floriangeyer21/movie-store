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
public class MovieSessionResponseDto {
    private Long id;
    private LocalDateTime showTime;
    private Long movieId;
    private String movieTitle;
    private String cinemaHallId;
}
