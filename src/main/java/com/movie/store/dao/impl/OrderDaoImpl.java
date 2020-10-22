package com.movie.store.dao.impl;

import com.movie.store.dao.OrderDao;
import com.movie.store.exceptions.DataProcessingException;
import com.movie.store.model.Order;
import com.movie.store.model.User;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Log4j
@Repository
public class OrderDaoImpl implements OrderDao {
    private final SessionFactory sessionFactory;

    public OrderDaoImpl(SessionFactory sessionFActory) {
        this.sessionFactory = sessionFActory;
    }

    @Override
    public Order add(Order order) {
        log.info("Calling method add() in OrderDaoImpl");
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            log.info("Successfully insert order entity. " + order);
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(
                    "Can't insert order entity. " + order, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getByUser(User user) {
        log.info("Calling method getByUser() in OrderDaoImpl");
        try (Session session = sessionFactory.openSession()) {
            Query<Order> query = session.createQuery(
                    "select distinct o from Order o "
                            + "left join fetch o.tickets t "
                            + "join fetch o.user "
                            + "where o.user.id = :id ", Order.class);
            query.setParameter("id", user.getId());
            return query.getResultList();
        }
    }
}
