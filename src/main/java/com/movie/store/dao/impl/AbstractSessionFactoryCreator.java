package com.movie.store.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractSessionFactoryCreator {
    protected SessionFactory sessionFactory;

    @Autowired
    public AbstractSessionFactoryCreator(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
