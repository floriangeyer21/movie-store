package com.movie.store.service.mappers;

import com.movie.store.model.Movie;
import com.movie.store.model.dto.MovieRequestDto;
import com.movie.store.model.dto.MovieResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

@Log4j
@Component
public class MovieMapper {

    public Movie mapRequestDtoToMovie(MovieRequestDto movieRequestDto) {
        log.info("Calling method mapRequestDtoToMovie in MovieMapper, "
                + movieRequestDto);
        return Movie.builder()
                .description(movieRequestDto.getDescription())
                .title(movieRequestDto.getTitle()).build();
    }

    public MovieResponseDto mapMovieToResponseDto(Movie movie) {
        log.info("Calling method mapMovieToResponseDto in MovieMapper, " + movie);
        return MovieResponseDto.builder()
                .description(movie.getDescription()).title(movie.getTitle())
                .id(movie.getId()).build();
    }

    public List<MovieResponseDto> mapAllMovieToResponseDto(List<Movie> movies) {
        log.info("Calling method mapAllMovieToResponseDto in MovieMapper, " + movies);
        return movies.stream()
                .map(this::mapMovieToResponseDto)
                .collect(Collectors.toList());
    }
}
