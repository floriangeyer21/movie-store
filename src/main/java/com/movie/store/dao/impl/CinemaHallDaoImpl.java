package com.movie.store.dao.impl;

import com.movie.store.dao.interfaces.CinemaHallDao;
import com.movie.store.exceptions.DataProcessingException;
import com.movie.store.model.CinemaHall;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Log4j
@Repository
public class CinemaHallDaoImpl implements CinemaHallDao {
    private final SessionFactory sessionFactory;

    public CinemaHallDaoImpl(SessionFactory sessionFActory) {
        this.sessionFactory = sessionFActory;
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        log.info("Calling method add() in CinemaHallDaoImpl, " + cinemaHall);
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(cinemaHall);
            transaction.commit();
            log.info("Successfully insert cinema hall entity. " + cinemaHall);
            return cinemaHall;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert cinema hall entity. " + cinemaHall, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<CinemaHall> getAll() {
        log.info("Calling method getAll() in CinemaHallDaoImpl");
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<CinemaHall> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(CinemaHall.class);
            criteriaQuery.from(CinemaHall.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving all cinema halls. ", e);
        }
    }

    @Override
    public CinemaHall getById(Long id) {
        log.info("Calling method getById() in CinemaHallDaoImpl with id " + id);
        try (Session session = sessionFactory.openSession()) {
            return session.get(CinemaHall.class, id);
        } catch (Exception e) {
            throw new DataProcessingException(
                    "Can't retrieve cinema hall from db, by id " + id, e);
        }
    }
}
