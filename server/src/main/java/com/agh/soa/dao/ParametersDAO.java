package com.agh.soa.dao;


import com.agh.soa.Category;
import com.agh.soa.IntParameter;
import com.agh.soa.StringParameter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Named
@ApplicationScoped
public class ParametersDAO {

    EntityManager entityManager;

    public ParametersDAO() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Projekt");
        entityManager = factory.createEntityManager();
    }

    public List<IntParameter> findByCategoryId(int id) {
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
        Query query = entityManager.createQuery("SELECT (p.label) FROM IntParameter p where p.categoriesByCategoryId.id=:id and p.value is null");
        query.setParameter("id", id);
        return (String) query.getSingleResult();
    }

    public List findIntParametersByElementId(int id) {
        Query query = entityManager.createQuery("FROM IntParameter p where p.elementByElementId.id=:id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<StringParameter> findStringParametersByElementId(int id) {
        Query query = entityManager.createQuery("FROM StringParameter p where p.elementByElementId.id=:id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<IntParameter> findParametersLabelsByCategoryId(int id) {
        Query query = entityManager.createQuery("FROM IntParameter p where p.elementByElementId.id=:id", IntParameter.class);
        query.setParameter("id",id);
        return query.getResultList();
    }

    public List<IntParameter> findParametersLabelsByElementId(int id) {
        Query query = entityManager.createQuery("FROM IntParameter p where p.elementByElementId.id=:id", IntParameter.class);
        query.setParameter("id",id);
        return query.getResultList();
    }

    public void create(IntParameter parameter) {
        entityManager.getTransaction().begin();
        entityManager.persist(parameter);
        entityManager.getTransaction().commit();
    }

    public void createStringParameter(StringParameter stringParameter) {
        entityManager.getTransaction().begin();
        entityManager.persist(stringParameter);
        entityManager.getTransaction().commit();
    }
}
