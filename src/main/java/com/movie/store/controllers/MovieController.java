package com.movie.store.controllers;

import com.movie.store.model.Movie;
import com.movie.store.model.dto.MovieRequestDto;
import com.movie.store.model.dto.MovieResponseDto;
import com.movie.store.service.interfaces.MovieService;
import com.movie.store.service.mappers.MovieMapper;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j
@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieMapper movieMapper;
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieMapper movieMapper,
                           MovieService movieService) {
        this.movieMapper = movieMapper;
        this.movieService = movieService;
    }

    @PostMapping("/add")
    public MovieResponseDto addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        log.info("Calling method addMovie in MovieController, " + movieRequestDto);
        Movie movie = movieMapper.mapRequestDtoToMovie(movieRequestDto);
        movie.setId(movieService.add(movie).getId());
        return movieMapper.mapMovieToResponseDto(movie);
    }

    @GetMapping("/all")
    public List<MovieResponseDto> getAllMovie() {
        log.info("Calling method getAllMovie in MovieController");
        return movieMapper.mapAllMovieToResponseDto(movieService.getAll());
    }

}
