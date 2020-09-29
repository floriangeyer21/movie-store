package com.movie.store.service;

import com.movie.store.model.Movie;

import java.util.List;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}
