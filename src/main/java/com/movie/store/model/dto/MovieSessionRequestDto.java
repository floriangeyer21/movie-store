package com.movie.store.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieSessionRequestDto {
    private String showTime;
    private String movieId;
    private String cinemaHallId;
}
