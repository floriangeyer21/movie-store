package com.movie.store.service.interfaces;

import com.movie.store.model.Movie;
import java.util.List;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();

    Movie getById(Long id);
}
