package com.movie.store.dao.impl;

import com.movie.store.dao.interfaces.RoleDao;
import com.movie.store.exceptions.DataProcessingException;
import com.movie.store.model.Role;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Log4j
@Repository
public class RoleDaoImpl implements RoleDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Role role) {
        log.info("Calling method add() in RoleDaoImpl");
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
            log.info("Role successfully created: " + role);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add role " + role + "to database :", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Role getRoleByName(String roleName) {
        log.info("Calling method getRoleByName() in RoleDaoImpl");
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Role where roleName = :name", Role.class)
                    .setParameter("name", Role.RoleName.valueOf(roleName))
                    .getSingleResult();
        }
    }
}
