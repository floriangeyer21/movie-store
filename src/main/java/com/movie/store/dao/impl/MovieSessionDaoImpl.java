package com.movie.store.dao.impl;

import com.movie.store.dao.interfaces.MovieSessionDao;
import com.movie.store.exceptions.DataProcessingException;
import com.movie.store.model.MovieSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Log4j
@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {
    private final SessionFactory sessionFactory;

    public MovieSessionDaoImpl(SessionFactory sessionFActory) {
        this.sessionFactory = sessionFActory;
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            Query<MovieSession> query = session.createQuery(
                    "from MovieSession ms "
                            + "join fetch ms.movie m "
                            + "join fetch ms.cinemaHall c "
                            + "where ms.movie.id = :id "
                            + "and ms.showTime between :start and :end", MovieSession.class);
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
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(movieSession);
            transaction.commit();
            log.info("Successfully insert movie session entity. " + movieSession);
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
