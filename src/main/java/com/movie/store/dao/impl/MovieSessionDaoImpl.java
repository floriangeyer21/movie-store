package com.movie.store.dao.impl;

import com.movie.store.dao.MovieSessionDao;
import com.movie.store.exceptions.DataProcessingException;
import com.movie.store.lib.Dao;
import com.movie.store.model.MovieSession;
import com.movie.store.util.HibernateUtil;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery(
                    "from MovieSession a join fetch Movie b on a.movie.id = b.id "
                            + "join fetch CinemaHall c on a.cinemaHall.id = c.id "
                            + "where b.id = :id "
                            + "and show_time between :start and :end");
            query.setParameter("id", movieId);
            query.setParameter("start", date.atTime(LocalTime.MIN));
            query.setParameter("end", date.atTime(LocalTime.MAX));
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find movie session by movie id " + movieId, e);
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert movie session entity. "
                    + movieSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
