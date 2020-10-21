package com.movie.store.dao.impl;

import com.movie.store.dao.ShoppingCartDao;
import com.movie.store.exceptions.DataProcessingException;
import com.movie.store.model.ShoppingCart;
import com.movie.store.model.User;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Log4j
@Repository
public class ShoppingCartDaoImpl extends AbstractSessionFactoryCreator implements ShoppingCartDao {

    public ShoppingCartDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        log.info("Calling method add() in ShoppingCartDao");
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
            log.info("Successfully insert shopping cart entity. " + shoppingCart);
            return shoppingCart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(
                    "Can't insert shoppingCart entity. " + shoppingCart, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        log.info("Calling method getByUser() in ShoppingCartDao");
        try (Session session = sessionFactory.openSession()) {
            Query<ShoppingCart> query = session.createQuery(
                    "from ShoppingCart s "
                            + "left join fetch s.tickets t "
                            + "join fetch s.user "
                            + "where s.user.id = :id ", ShoppingCart.class);
            query.setParameter("id", user.getId());
            log.info("Successfully update shopping cart entity with user id " + user.getId()
                    + " and email " + user.getEmail());
            return query.uniqueResult();
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        log.info("Calling method update() in ShoppingCartDao");
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(
                    "Can't update shoppingCart entity. " + shoppingCart, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
