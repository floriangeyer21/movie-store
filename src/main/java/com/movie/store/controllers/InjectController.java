package com.movie.store.controllers;

import com.movie.store.model.CinemaHall;
import com.movie.store.service.interfaces.CinemaHallService;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j
@RestController
@RequestMapping("/inject")
public class InjectController {
    private final CinemaHallService cinemaHallServcie;

    public InjectController(CinemaHallService cinemaHallServcie) {
        this.cinemaHallServcie = cinemaHallServcie;
    }

    @GetMapping("/cinema_hall")
    public void injectCinemaHall() {
        log.info("Calling method injectCinemaHall in InjectController");
        CinemaHall cinemaHall = CinemaHall.builder()
                .capacity(200).build();
        cinemaHallServcie.add(cinemaHall);
    }
}
