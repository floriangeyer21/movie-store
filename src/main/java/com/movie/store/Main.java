package com.movie.store;

import com.movie.store.configuration.ApplicationConfig;
import com.movie.store.exceptions.AuthenticationException;
import com.movie.store.model.CinemaHall;
import com.movie.store.model.Movie;
import com.movie.store.model.MovieSession;
import com.movie.store.model.Order;
import com.movie.store.model.ShoppingCart;
import com.movie.store.model.User;
import com.movie.store.security.AuthenticationService;
import com.movie.store.service.interfaces.CinemaHallService;
import com.movie.store.service.interfaces.MovieService;
import com.movie.store.service.interfaces.MovieSessionService;
import com.movie.store.service.interfaces.OrderService;
import com.movie.store.service.interfaces.ShoppingCartService;
import com.movie.store.service.interfaces.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Log4j
public class Main {

    public static void main(String[] args) throws AuthenticationException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ApplicationConfig.class);
        MovieService movieService = context.getBean(MovieService.class);
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
        MovieSession movieSession1 = new MovieSession();
        movieSession1.setMovie(movie);
        LocalDateTime dateTime1 = LocalDateTime.of(2020, 12, 12, 15, 30);
        movieSession1.setShowTime(dateTime1);
        movieSession1.setCinemaHall(cinemaHall);
        cinemaHall.setCapacity(100);
        CinemaHallService cinemaHallService = context.getBean(CinemaHallService.class);
        cinemaHallService.add(cinemaHall);
        MovieSessionService movieSessionService = context.getBean(MovieSessionService.class);
        movieSessionService.add(movieSession);
        movieSessionService.add(movieSession1);
        log.info("Create new entity: " + cinemaHall);
        log.info("Add to db entity: " + movieSession);
        LocalDate date = LocalDate.of(2020, 12, 12);
        log.info("Result of findAvailableSessions method with movie id" + movie.getId()
                + " and date " + dateTime
                + ": " + movieSessionService.findAvailableSessions(movie.getId(), date));
        log.info("Result of getAll() methods in movieService: "
                + movieService.getAll());
        log.info("Result of getAll() methods in cinemaHallService: "
                + cinemaHallService.getAll());
        UserService userService = context.getBean(UserService.class);
        AuthenticationService authenticationService = context.getBean(AuthenticationService.class);
        log.info("Register a new user. "
                + authenticationService.register("best@email.ever", "1234"));
        try {
            authenticationService.login("best@email.ever", "1234");
        } catch (Exception e) {
            log.warn("Log in error. ", e);
        }

        User user = userService.findByEmail("best@email.ever").get();
        ShoppingCartService shoppingCartService
                = context.getBean(ShoppingCartService.class);
        shoppingCartService.addSession(movieSession, user);
        shoppingCartService.addSession(movieSession1, user);
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        log.info("test getByUser() " + shoppingCart);
        OrderService orderService = context.getBean(OrderService.class);
        Order order = orderService.completeOrder(shoppingCart.getTickets(), user);
        orderService.getOrderHistory(user).forEach(log::info);
        ShoppingCart shoppingCart1 = shoppingCartService.getByUser(user);
        log.info("test clear method " + shoppingCartService.clear(shoppingCart));
        log.info(orderService.getOrderHistory(user));
    }
}
