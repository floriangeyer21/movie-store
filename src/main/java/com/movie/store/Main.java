package com.movie.store;

import com.movie.store.lib.Injector;
import com.movie.store.model.Movie;
import com.movie.store.service.MovieService;

public class Main {
    private static Injector injector = Injector.getInstance("com.movie.store");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);

        Movie movie = new Movie();
        movie.setTitle("Rain Man");
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);
    }
}
