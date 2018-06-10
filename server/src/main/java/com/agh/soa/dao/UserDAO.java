package com.agh.soa.dao;

import com.agh.soa.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@Named
@ApplicationScoped
public class UserDAO {

    EntityManager entityManager;

    public UserDAO() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Projekt");
        entityManager = factory.createEntityManager();
    }


    public User getUserById(Integer id) {
        Query query =entityManager.createNamedQuery("findUserById", User.class);
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }
}
