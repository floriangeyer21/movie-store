package com.movie.store.dao.impl;

import com.movie.store.dao.interfaces.MovieDao;
import com.movie.store.exceptions.DataProcessingException;
import com.movie.store.model.Movie;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Log4j
@Repository
public class MovieDaoImpl implements MovieDao {
    private final SessionFactory sessionFactory;

    public MovieDaoImpl(SessionFactory sessionFActory) {
        this.sessionFactory = sessionFActory;
    }

    @Override
    public Movie add(Movie movie) {
        log.info("Calling method add() in MovieDaoImpl, " + movie);
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
            log.info("Successfully insert movie entity. " + movie);
            return movie;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert movie entity. " + movie, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Movie> getAll() {
        log.info("Calling method getAll() in MovieDaoImpl");
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Movie> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Movie.class);
            criteriaQuery.from(Movie.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving all movies. ", e);
        }
    }

    @Override
    public Movie getById(Long id) {
        log.info("Calling method getById() in MovieDaoImpl, id = " + id);
        try (Session session = sessionFactory.openSession()) {
            return session.get(Movie.class, id);
        } catch (Exception e) {
            throw new DataProcessingException(
                    "Can't retrieve movie from db by id: " + id, e);
        }
    }
}
