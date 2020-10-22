package com.movie.store.service.mappers;

import com.movie.store.model.CinemaHall;
import com.movie.store.model.Movie;
import com.movie.store.model.MovieSession;
import com.movie.store.model.dto.MovieSessionRequestDto;
import com.movie.store.model.dto.MovieSessionResponseDto;
import com.movie.store.service.interfaces.CinemaHallService;
import com.movie.store.service.interfaces.MovieService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

@Log4j
@Component
public class MovieSessionMapper {
    private final CinemaHallService cinemaHallService;
    private final MovieService movieService;

    public MovieSessionMapper(CinemaHallService cinemaHallService,
                              MovieService movieService) {
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
    }

    public MovieSession mapRequestDtoToMovieSession(
            MovieSessionRequestDto movieSessionRequestDto) {
        log.info("Calling method mapRequestDtoToMovieSession in movieSessionMapper, "
                + movieSessionRequestDto);
        CinemaHall cinemaHall =
                cinemaHallService.getById(Long.valueOf(movieSessionRequestDto.getCinemaHallId()));
        Movie movie = movieService.getById(Long.valueOf(movieSessionRequestDto.getMovieId()));
        return MovieSession.builder()
                .movie(movie)
                .showTime(LocalDateTime.parse(movieSessionRequestDto.getShowTime()))
                .cinemaHall(cinemaHall).build();
    }

    public MovieSessionResponseDto mapMovieSessionToResponseDto(MovieSession movieSession) {
        log.info("Calling method mapMovieSessionToResponseDto in movieSessionMapper, "
                + movieSession);
        return MovieSessionResponseDto.builder()
                .id(movieSession.getId())
                .cinemaHallId(movieSession.getCinemaHall().getId().toString())
                .movieTitle(movieSession.getMovie().getTitle())
                .showTime(movieSession.getShowTime().toString()).build();
    }

    public List<MovieSessionResponseDto> mapAllMovieSessionToResponseDto(
            List<MovieSession> movieSessions) {
        log.info("Calling method mapAllMovieSessionToResponseDto in movieSessionMapper, "
                + movieSessions);
        return movieSessions.stream()
                .map(this::mapMovieSessionToResponseDto)
                .collect(Collectors.toList());
    }
}
