package com.agh.soa.game.controller;

import com.agh.soa.Category;
import com.agh.soa.CategoryType;
import com.agh.soa.Element;
import com.agh.soa.game.event.ElementChangeEvent;
import org.primefaces.push.EventBusFactory;
import remote.RemoteCategoryService;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named
@ManagedBean
@SessionScoped
public class CatalogController implements Serializable {

    @EJB(mappedName = "java:global/server-1.0-SNAPSHOT/CategoryService!remote.RemoteCategoryService")
    private RemoteCategoryService remoteCategoryService;

    @Inject
    private Event<ElementChangeEvent> elementChangeEventEvent;
    private List<Element> powerfulElements;


    public List<Element> getElementsByCategoryId(int id) {
        return remoteCategoryService.getElementsByCategoryId(id);
    }

    public List<CategoryType> getCategoryTypes() { return remoteCategoryService.getCategoryTypes(); }

    public List<Category> getCategoriesByType(int typeId) { return remoteCategoryService.getCategoriesByType(typeId); }

    public void removeCategory(Category category) throws IOException {
        remoteCategoryService.deleteCategory(category);
        fireElementEvent();

    }

    public void getMostPowerfulElements(@Observes ElementChangeEvent elementChangeEvent) {
        powerfulElements = remoteCategoryService.getMostPowerfulElements();
        org.primefaces.push.EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish("/notify", new FacesMessage(powerfulElements.toString()));
        //return remoteCategoryService.getMostPowerfulElements();
    }

    public void removeElement(Element element) {
        remoteCategoryService.deleteElement(element);
        fireElementEvent();
    }


    private void fireElementEvent() {
        ElementChangeEvent event = new ElementChangeEvent();
        elementChangeEventEvent.fire(event);
    }

    public List<Element> getPowerfulElements() {
        return powerfulElements;
    }
}
