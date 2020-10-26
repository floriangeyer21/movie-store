package com.movie.store.service.mappers;

import com.movie.store.model.CinemaHall;
import com.movie.store.model.dto.CinemaHallRequestDto;
import com.movie.store.model.dto.CinemaHallResponseDto;
import com.movie.store.service.interfaces.CinemaHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CinemaHallMapper {

    public CinemaHall mapRequestDtoToCinemaHall(CinemaHallRequestDto cinemaHallRequestDto) {
        return CinemaHall.builder()
                .capacity(cinemaHallRequestDto.getCapacity()).build();
    }

    public CinemaHallResponseDto mapCinemaHallToResponseDto(CinemaHall cinemaHall) {
        return CinemaHallResponseDto.builder()
                .capacity(cinemaHall.getCapacity())
                .id(cinemaHall.getId()).build();
    }

    public List<CinemaHallResponseDto> mapAllCinemaHallToResponseDto(List<CinemaHall> cinemaHalls) {
        return cinemaHalls.stream()
                .map(this::mapCinemaHallToResponseDto)
                .collect(Collectors.toList());
    }
}
