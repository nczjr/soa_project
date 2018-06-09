package com.agh.soa.dao;

import com.agh.soa.Element;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Named
@ApplicationScoped
public class ElementDAO {

    EntityManager entityManager;

    public ElementDAO() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Projekt");
        entityManager = factory.createEntityManager();
    }

    public List<Element> findAll() {
        Query query = entityManager.createQuery("FROM Element ", Element.class);
        return query.getResultList();
    }

    public List<Element> findByCategoryId(int id) {
        Query query = entityManager.createQuery("from  Element e where e.categories.id=:id", Element.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
