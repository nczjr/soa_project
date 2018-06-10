package com.agh.soa.dao;

import com.agh.soa.Category;
import com.agh.soa.CategoryType;
import com.agh.soa.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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

    public List<CategoryType> findCategoryTypes() {
        Query query = entityManager.createNamedQuery("getCategoryTypes");
        return query.getResultList();
    }

    public List<Category> findByCategoryType(int typeId) {
        Query query = entityManager.createQuery("FROM Category c where c.categoryTypesByTypeId.id=:typeId");
        query.setParameter("typeId", typeId);
        return query.getResultList();

    }

    public void create(Category obj) {
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();
    }


    public void deleteCategory(Category category) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(category) ? category : entityManager.merge(category));
        entityManager.getTransaction().commit();
    }

    public void edit(Category category) {
        entityManager.getTransaction().begin();
        entityManager.merge(category);
        entityManager.getTransaction().commit();
    }





}
