package com.movie.store;

import com.movie.store.lib.Injector;
import com.movie.store.model.CinemaHall;
import com.movie.store.model.Movie;
import com.movie.store.model.MovieSession;
import com.movie.store.service.CinemaHallService;
import com.movie.store.service.MovieService;
import com.movie.store.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import lombok.extern.log4j.Log4j;

@Log4j
public class Main {
    private static Injector injector = Injector.getInstance("com.movie.store");

    public static void main(String[] args) {
        MovieService movieService =
                (MovieService) injector.getInstance(MovieService.class);
        Movie movie = new Movie();
        movie.setTitle("Rain Man");
        movieService.add(movie);
        log.info("Create new entity: " + movie);
        MovieSession movieSession = new MovieSession();
        CinemaHall cinemaHall = new CinemaHall();
        movieSession.setMovie(movie);
        LocalDate dateTime = LocalDate.of(2020, 12, 12);
        movieSession.setShowTime(dateTime);
        movieSession.setCinemaHall(cinemaHall);
        cinemaHall.setCapacity(100);
        cinemaHall.setMovieSessions(List.of(movieSession));
        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        CinemaHallService cinemaHallService
                = (CinemaHallService) injector.getInstance(CinemaHallService.class);
        movieSessionService.add(movieSession);
        cinemaHallService.add(cinemaHall);
        log.info("Create new entity: " + cinemaHall);
        log.info("Add to db entity: " + movieSession);
        log.info("Result of findAvailableSessions method with movie id" + movie.getId()
                + " and date " + dateTime
                + ": " + movieSessionService.findAvailableSessions(movie.getId(), dateTime));
        log.info("Result of getAll() methods in movieService: "
                + movieService.getAll());
        log.info("Result of getAll() methods in cinemaHallService: "
                + cinemaHallService.getAll());
    }
}
