package com.movie.store.service.impl;

import com.movie.store.dao.MovieDao;
import com.movie.store.lib.Inject;
import com.movie.store.lib.Service;
import com.movie.store.model.Movie;
import com.movie.store.service.MovieService;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Inject
    private MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
