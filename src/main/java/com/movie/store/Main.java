package com.movie.store;

import com.movie.store.exceptions.AuthenticationException;
import com.movie.store.lib.Injector;
import com.movie.store.model.CinemaHall;
import com.movie.store.model.Movie;
import com.movie.store.model.MovieSession;
import com.movie.store.model.ShoppingCart;
import com.movie.store.model.User;
import com.movie.store.security.AuthenticationService;
import com.movie.store.service.CinemaHallService;
import com.movie.store.service.MovieService;
import com.movie.store.service.MovieSessionService;
import com.movie.store.service.OrderService;
import com.movie.store.service.ShoppingCartService;
import com.movie.store.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.log4j.Log4j;

@Log4j
public class Main {
    private static Injector injector = Injector.getInstance("com.movie.store");

    public static void main(String[] args) throws AuthenticationException {
        MovieService movieService =
                (MovieService) injector.getInstance(MovieService.class);
        Movie movie = new Movie();
        movie.setTitle("Rain Man");
        movieService.add(movie);
        log.info("Create new entity: " + movie);
        MovieSession movieSession = new MovieSession();
        CinemaHall cinemaHall = new CinemaHall();
        movieSession.setMovie(movie);
        LocalDateTime dateTime = LocalDateTime.of(2020, 12, 12, 15, 30);
        movieSession.setShowTime(dateTime);
        movieSession.setCinemaHall(cinemaHall);
        cinemaHall.setCapacity(100);
        cinemaHall.setMovieSessions(List.of(movieSession));
        CinemaHallService cinemaHallService
                = (CinemaHallService) injector.getInstance(CinemaHallService.class);
        cinemaHallService.add(cinemaHall);
        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);
        log.info("Create new entity: " + cinemaHall);
        log.info("Add to db entity: " + movieSession);
        LocalDate date = LocalDate.of(2020, 12, 13);
        log.info("Result of findAvailableSessions method with movie id" + movie.getId()
                + " and date " + dateTime
                + ": " + movieSessionService.findAvailableSessions(movie.getId(), date));
        log.info("Result of getAll() methods in movieService: "
                + movieService.getAll());
        log.info("Result of getAll() methods in cinemaHallService: "
                + cinemaHallService.getAll());
        UserService userService =
                (UserService) injector.getInstance(UserService.class);
        AuthenticationService authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);
        log.info("Register new user " + authenticationService.register("best@email.ever", "1234"));
        log.info("Find user by email " + authenticationService.login("best@email.ever", "1234"));

        User user = userService.findByEmail("best@email.ever").get();
        ShoppingCartService shoppingCartService
                = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        shoppingCartService.addSession(movieSession, user);
        log.info("test getByUser() " + shoppingCartService.getByUser(user));
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
        log.info(orderService.completeOrder(shoppingCart.getTickets(), user));
        log.info(orderService.getOrderHistory(user));
        log.info(shoppingCartService.getByUser(user));
        log.info("test clear method " + shoppingCartService.clear(shoppingCart));
        log.info(orderService.getOrderHistory(user));
    }
}
