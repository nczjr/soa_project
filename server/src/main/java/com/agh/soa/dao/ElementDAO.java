package com.agh.soa.dao;

import com.agh.soa.Element;
import com.agh.soa.ElementType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import java.util.List;

@Named
@ApplicationScoped
public class ElementDAO {

    @Inject
    EntityManager entityManager;

    public ElementDAO() {
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

    public void editElementField(Element element) {
        entityManager.getTransaction().begin();
        entityManager.merge(element);
        entityManager.getTransaction().commit();
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

    public Element getMostPowerfulElement(int typeId) {
        try {
            Query query = entityManager.createNativeQuery(" select * from elements e where powerValue = (SELECT max(powerValue) from elements b where e.type_id=b.type_id group by type_id ) and e.type_id=:id",Element.class);
            query.setParameter("id", typeId);
            return query.getResultList().size() > 1 ? (Element) query.getResultList().get(0) : (Element) query.getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }

    public void createElementType(ElementType elementType) {
        entityManager.getTransaction().begin();
        entityManager.persist(elementType);
        entityManager.getTransaction().commit();
    }

    public void deleteElement(Element element) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE Element e where e.id=:id").setParameter("id",element.getId()).executeUpdate();
//        entityManager.remove(entityManager.contains(element) ? element : entityManager.merge(element));
        entityManager.getTransaction().commit();
    }


    public List<Element> findMostPowerfulElements() {
        Query query = entityManager.createNativeQuery("select * from elements e where powerValue = (SELECT max(powerValue) from elements b where e.type_id=b.type_id group by type_id ) group by type_id", Element.class);
        return query.getResultList();
    }
}
