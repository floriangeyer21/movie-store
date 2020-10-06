package com.movie.store.service.impl;

import com.movie.store.dao.CinemaHallDao;
import com.movie.store.lib.Inject;
import com.movie.store.lib.Service;
import com.movie.store.model.CinemaHall;
import com.movie.store.service.CinemaHallService;
import java.util.List;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    private CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
