package com.movie.store.dao.impl;

import com.movie.store.dao.OrderDao;
import com.movie.store.exceptions.DataProcessingException;
import com.movie.store.lib.Dao;
import com.movie.store.model.Order;
import com.movie.store.model.User;
import com.movie.store.util.HibernateUtil;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
@Log4j
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order add(Order order) {
        log.info("Calling method add() in OrderDaoImpl");
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
