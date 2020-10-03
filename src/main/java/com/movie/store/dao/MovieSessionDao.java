package com.movie.store.dao;

import com.movie.store.model.MovieSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MovieSessionDao {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDateTime date);

    MovieSession add(MovieSession session);
}
