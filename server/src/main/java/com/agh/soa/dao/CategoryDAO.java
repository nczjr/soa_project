package com.agh.soa.dao;

import com.agh.soa.entity.Category;
import com.agh.soa.entity.IntParameter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Named
@ApplicationScoped
public class CategoryDAO implements Serializable {

    private EntityManager entityManager;


    public CategoryDAO() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Projekt");
        entityManager = factory.createEntityManager();
    }

    public List<Category> findAll() {
        Query query = entityManager.createQuery("FROM Category", Category.class);
        return query.getResultList();
    }






}
