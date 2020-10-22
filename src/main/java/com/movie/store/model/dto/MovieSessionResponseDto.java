package com.movie.store.model.dto;

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
    private String showTime;
    private String movieTitle;
    private String cinemaHallId;
}
