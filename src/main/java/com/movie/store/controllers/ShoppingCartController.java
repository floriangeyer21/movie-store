package com.movie.store.controllers;

import com.movie.store.model.MovieSession;
import com.movie.store.model.User;
import com.movie.store.model.dto.ShoppingCartResponseDto;
import com.movie.store.service.interfaces.MovieSessionService;
import com.movie.store.service.interfaces.ShoppingCartService;
import com.movie.store.service.interfaces.UserService;
import com.movie.store.service.mappers.ShoppingCartMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartMapper shoppingCartMapper;
    private final UserService userService;
    private final MovieSessionService movieSessionService;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  UserService userService,
                                  MovieSessionService movieSessionService,
                                  ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.movieSessionService = movieSessionService;
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @PostMapping("/movie-sessions")
    public void addMovieSessions(@RequestParam("userId") Long userId,
                                 @RequestBody Long movieSessionId) {
        User user = userService.findById(userId).get();
        MovieSession movieSession = movieSessionService.findById(movieSessionId);
        shoppingCartService.addSession(movieSession, user);
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(@RequestParam Long userId) {
        return shoppingCartMapper.mapShoppingCartToResponseDto(
                shoppingCartService.getByUser(userService.findById(userId).get()));
    }
}