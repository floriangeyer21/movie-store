package com.movie.store.service;

import com.movie.store.model.CinemaHall;
import java.util.List;

public interface CinemaHallService {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();
}
