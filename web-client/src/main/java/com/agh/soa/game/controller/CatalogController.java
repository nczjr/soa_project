package com.agh.soa.game.controller;

import com.agh.soa.*;
import remote.RemoteCategoryService;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Named
@ManagedBean
public class CatalogController {

    @EJB(mappedName = "java:global/server-1.0-SNAPSHOT/CategoryService!remote.RemoteCategoryService")
    private RemoteCategoryService remoteCategoryService;


    public List<Element> getElementsByCategoryId(int id) {
        return remoteCategoryService.getElementsByCategoryId(id);
    }

    public List<CategoryType> getCategoryTypes() { return remoteCategoryService.getCategoryTypes(); }

    public List<Category> getCategoriesByType(int typeId) { return remoteCategoryService.getCategoriesByType(typeId); }

    public void removeCategory(Category category) throws IOException {
        remoteCategoryService.deleteCategory(category);

    }

    public List<Element> getMostPowerfulElements() {
        return remoteCategoryService.getMostPowerfulElements();
    }

    public void removeElement(Element element) throws IOException {
        remoteCategoryService.deleteElement(element);
    }

    public void reload() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
}
