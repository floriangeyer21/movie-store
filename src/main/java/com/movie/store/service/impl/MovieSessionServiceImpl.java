package com.movie.store.service.impl;

import com.movie.store.dao.interfaces.MovieSessionDao;
import com.movie.store.model.MovieSession;
import com.movie.store.service.interfaces.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    private final MovieSessionDao movieSessionDao;

    @Autowired
    public MovieSessionServiceImpl(MovieSessionDao movieSessionDao) {
        this.movieSessionDao = movieSessionDao;
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public MovieSession add(MovieSession session) {
        return movieSessionDao.add(session);
    }

    public MovieSessionDao getMovieSessionDao() {
        return movieSessionDao;
    }

    @Override
    public MovieSession findById(Long id) {
        return movieSessionDao.findById(id);
    }
}
