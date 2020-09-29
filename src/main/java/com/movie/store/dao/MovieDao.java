package com.movie.store.dao;

import com.movie.store.model.Movie;

import java.util.List;

public interface MovieDao {
    Movie add(Movie movie);

    List<Movie> getAll();
}
