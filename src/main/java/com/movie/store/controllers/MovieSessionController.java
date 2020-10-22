package com.movie.store.controllers;

import com.movie.store.model.MovieSession;
import com.movie.store.model.dto.MovieSessionRequestDto;
import com.movie.store.model.dto.MovieSessionResponseDto;
import com.movie.store.service.interfaces.MovieSessionService;
import com.movie.store.service.mappers.MovieSessionMapper;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j
@RestController
@RequestMapping("/movie_session")
public class MovieSessionController {
    private final MovieSessionMapper movieSessionMapper;
    private final MovieSessionService movieSessionService;

    @Autowired
    public MovieSessionController(MovieSessionMapper movieSessionMapper,
                                  MovieSessionService movieSessionSErvice) {
        this.movieSessionService = movieSessionSErvice;
        this.movieSessionMapper = movieSessionMapper;
    }

    @PostMapping("/add")
    public MovieSessionResponseDto addMovieSession(
            @RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        log.info("Calling method addMovieSession in MovieSessionController, "
                + movieSessionRequestDto);
        MovieSession movieSession =
                movieSessionMapper.mapRequestDtoToMovieSession(movieSessionRequestDto);
        movieSession = movieSessionService.add(movieSession);
        return movieSessionMapper.mapMovieSessionToResponseDto(movieSession);
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAllMovieSession(@RequestParam("movie_id")
                                                                        Long movieId,
                                                            @RequestParam("show_time")
                                                                    String showTime) {
        log.info("Calling method getAllMovieSession in MovieSessionController, movie id = "
                + movieId + ", show time =  " + movieId);
        return movieSessionMapper.mapAllMovieSessionToResponseDto(
                movieSessionService.findAvailableSessions(
                        movieId, LocalDateTime.parse(showTime).toLocalDate()));
    }
}
