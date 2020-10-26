package com.movie.store.controllers;

import com.movie.store.model.CinemaHall;
import com.movie.store.model.dto.CinemaHallRequestDto;
import com.movie.store.model.dto.CinemaHallResponseDto;
import com.movie.store.service.interfaces.CinemaHallService;
import com.movie.store.service.mappers.CinemaHallMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private CinemaHallService cinemaHallService;
    private CinemaHallMapper cinemaHallMapper;

    @Autowired
    public CinemaHallController(CinemaHallService cinemaHallService,
                                CinemaHallMapper cinemaHallMapper) {
        this.cinemaHallService = cinemaHallService;
        this.cinemaHallMapper = cinemaHallMapper;
    }

    @PostMapping
    public void addCinemaHalls(@RequestBody CinemaHallRequestDto cinemaHallRequestDto) {
        CinemaHall cinemaHall = cinemaHallMapper.mapRequestDtoToCinemaHall(cinemaHallRequestDto);
        cinemaHallService.add(cinemaHall);
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAllCinemaHall() {
        return cinemaHallMapper.mapAllCinemaHallToResponseDto(
                cinemaHallService.getAll());
    }
}
