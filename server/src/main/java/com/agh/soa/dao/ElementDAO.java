package com.agh.soa.dao;

import com.agh.soa.Element;
import com.agh.soa.ElementType;

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
        Query query = entityManager.createQuery("from  Element e where e.categoriesByCategoryId.id=:id", Element.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Element> findByElementType(int id) {
        Query query = entityManager.createQuery("FROM Element e where e.categoriesByCategoryId.categoryTypesByTypeId.id=:id", Element.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public ElementType findElemenTypeByTypeId(int id) {
        Query query = entityManager.createQuery("FROM ElementType e where e.id=:id", ElementType.class);
        query.setParameter("id", id);
        return (ElementType) query.getSingleResult();
    }

    public void create(Element element) {
        entityManager.getTransaction().begin();
        entityManager.persist(element);
        entityManager.getTransaction().commit();
    }

    public void deleteElement(Element element) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(element) ? element : entityManager.merge(element));
        entityManager.getTransaction().commit();
    }


}
