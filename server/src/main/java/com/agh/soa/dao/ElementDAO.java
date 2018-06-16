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

    public List<Element> findByCategoryId(int id) {
        Query query = entityManager.createQuery("from  Element e where e.categoriesByCategoryId.id=:id", Element.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Element> findByElementType(int id) {
        Query query = entityManager.createQuery("FROM Element e where e.elementTypesByTypeId.id=:id", Element.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public ElementType findElemenTypeByTypeId(int id) {
        Query query = entityManager.createQuery("FROM ElementType e where e.id=:id", ElementType.class);
        query.setParameter("id", id);
        return (ElementType) query.getSingleResult();
    }

    public List<ElementType> findElementTypes() {
        Query query = entityManager.createNamedQuery("getElementTypes", ElementType.class);
        return query.getResultList();

    }

    public void create(Element element) {
        entityManager.getTransaction().begin();
        entityManager.persist(element);
        entityManager.getTransaction().commit();
    }

    public void edit(Element element) {
        entityManager.getTransaction().begin();
        entityManager.merge(element);
        entityManager.getTransaction().commit();
    }


    public void deleteElement(Element element) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(element) ? element : entityManager.merge(element));
        entityManager.getTransaction().commit();
    }


    public List<Element> findMostPowerfulElements() {
        Query query = entityManager.createNativeQuery("select * from elements e where powerValue = (SELECT max(powerValue) from elements b where e.type_id=b.type_id group by type_id ) group by type_id", Element.class);
        return query.getResultList();
    }
}
