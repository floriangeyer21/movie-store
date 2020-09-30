package com.movie.store;

import com.movie.store.lib.Injector;
import com.movie.store.model.Movie;
import com.movie.store.service.MovieService;
import lombok.extern.log4j.Log4j;

@Log4j
public class Main {
    private static Injector injector = Injector.getInstance("com.movie.store");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        Movie movie = new Movie();
        movie.setTitle("Rain Man");
        log.info("Create new entity: " + movie);
        movieService.add(movie);
        log.info("Add to db entity: " + movie);
        movieService.getAll().forEach(System.out::println);
    }
}
