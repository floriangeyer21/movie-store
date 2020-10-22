package com.movie.store.dao.interfaces;

import com.movie.store.model.Movie;
import java.util.List;

public interface MovieDao {
    Movie add(Movie movie);

    List<Movie> getAll();

    Movie getById(Long id);
}
