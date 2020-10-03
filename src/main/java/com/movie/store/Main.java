package com.movie.store;

import com.movie.store.lib.Injector;
import com.movie.store.model.CinemaHall;
import com.movie.store.model.Movie;
import com.movie.store.model.MovieSession;
import com.movie.store.service.CinemaHallService;
import com.movie.store.service.MovieService;
import com.movie.store.service.MovieSessionService;
import com.movie.store.service.impl.MovieSessionServiceImpl;
import lombok.extern.log4j.Log4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Log4j
public class Main {
    private static Injector injector = Injector.getInstance("com.movie.store");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        Movie movie = new Movie();
        LocalDateTime dateTime = LocalDateTime.of(2020, 12, 12, 12, 34);
        movie.setTitle("Rain Man");
        MovieSession movieSession = new MovieSession();
        MovieSessionService movieSessionService = (MovieSessionService) injector.getInstance(MovieSessionService.class);
        log.info("Create new entity: " + movie);
        movie = movieService.add(movie);
        movieSession.setMovie(movie);
        movieSession.setShowTime(dateTime);
        movieSession = movieSessionService.add(movieSession);
        log.info("Add to db entity: " + movie);
        System.out.println(movieSessionService.findAvailableSessions(movie.getId(), dateTime));
        movieService.getAll().forEach(System.out::println);
        CinemaHallService cinemaHallService
                = (CinemaHallService) injector.getInstance(CinemaHallService.class);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setMovieSessions(List.of(movieSession));
        cinemaHallService.add(cinemaHall);
        System.out.println(cinemaHallService.getAll());
    }
}
