package com.movie.store.controllers;

import com.movie.store.model.MovieSession;
import com.movie.store.model.User;
import com.movie.store.model.dto.ShoppingCartResponseDto;
import com.movie.store.service.interfaces.MovieSessionService;
import com.movie.store.service.interfaces.ShoppingCartService;
import com.movie.store.service.interfaces.UserService;
import com.movie.store.service.mappers.ShoppingCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartMapper shoppingCartMapper;
    private final UserService userService;
    private final MovieSessionService movieSessionService;

    @Autowired
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
    public void addMovieSession(Authentication authentication,
                                @RequestParam Long id) {
        User user = userService.findByEmail(authentication.getName());
        MovieSession movieSession = movieSessionService.findById(id);
        shoppingCartService.addSession(movieSession, user);
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(Authentication authentication) {
        return shoppingCartMapper.mapShoppingCartToResponseDto(
                shoppingCartService.getByUser(userService.findByEmail(
                        authentication.getName())));
    }
}
