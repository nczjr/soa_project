package com.agh.soa.dao;

import com.agh.soa.entity.Category;
import com.agh.soa.entity.IntParameter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Named
@ApplicationScoped
public class IntParametersDAO {

    EntityManager entityManager;

    public IntParametersDAO() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Projekt");
        entityManager = factory.createEntityManager();
    }

    public List findByCategoryId(int id) {
        Query query = entityManager.createNamedQuery("findByCategoryId", IntParameter.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List findByUserId(int id) {
        Query query = entityManager.createQuery(" FROM IntParameter p where p.userId=:id", Category.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public String findParameterLabelByCategoryId(int id) {
        Query query = entityManager.createQuery("SELECT (p.label) FROM IntParameter p where p.categoryId=:id and p.value is null");
        query.setParameter("id", id);
        return (String) query.getSingleResult();
    }
}
