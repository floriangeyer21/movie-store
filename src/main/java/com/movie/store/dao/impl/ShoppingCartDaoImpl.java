package com.movie.store.dao.impl;

import com.movie.store.dao.ShoppingCartDao;
import com.movie.store.exceptions.DataProcessingException;
import com.movie.store.lib.Dao;
import com.movie.store.model.ShoppingCart;
import com.movie.store.model.User;
import com.movie.store.util.HibernateUtil;
import java.util.Optional;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Log4j
@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        log.info("Calling method add() in ShoppingCartDao");
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(shoppingCart);
            transaction.commit();
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
    public Optional<ShoppingCart> getByUser(User user) {
        log.info("Calling method getByUser() in ShoppingCartDao");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<ShoppingCart> query = session.createQuery(
                    "from ShoppingCart s "
                            + "left join fetch s.tickets "
                            + "left join fetch s.user "
                            + "where s.user.id = :id ", ShoppingCart.class);
            query.setParameter("id", user.getId());
            ShoppingCart shoppingCart = query.uniqueResult();
            return Optional.of(shoppingCart);
        }
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        log.info("Calling method update() in ShoppingCartDao");
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
            return shoppingCart;
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
