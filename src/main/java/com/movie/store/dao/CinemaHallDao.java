package com.movie.store.dao;

import com.movie.store.model.CinemaHall;

import java.util.List;

public interface CinemaHallDao {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();
}
