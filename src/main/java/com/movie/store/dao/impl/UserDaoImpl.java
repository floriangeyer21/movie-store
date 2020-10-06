package com.movie.store.dao.impl;

import com.movie.store.dao.UserDao;
import com.movie.store.exceptions.DataProcessingException;
import com.movie.store.lib.Dao;
import com.movie.store.model.User;
import com.movie.store.util.HibernateUtil;
import java.util.Optional;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User add(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert user entity. ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery(
                    "from User where email = :email ");
            query.setParameter("email", email);
            User user = (User) query.getSingleResult();
            return Optional.of(user);
        }
    }
}
