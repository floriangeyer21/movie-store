package com.movie.store.dao.impl;

import com.movie.store.dao.TicketDao;
import com.movie.store.exceptions.DataProcessingException;
import com.movie.store.model.Ticket;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Log4j
@Repository
public class TicketDaoImpl extends AbstractSessionFactoryCreator implements TicketDao {

    public TicketDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Ticket add(Ticket ticket) {
        log.info("Calling method add() in TicketDaoImpl");
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            log.info("Successfully insert ticket entity. " + ticket);
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert ticket entity. " + ticket, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
