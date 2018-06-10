package com.agh.soa.game;

import com.agh.soa.*;
import remote.RemoteCategoryService;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import java.util.List;

@Named
@ManagedBean
public class CategoriesController {

    @EJB(mappedName = "java:global/server-1.0-SNAPSHOT/CategoryService!remote.RemoteCategoryService")
    private RemoteCategoryService remoteCategoryService;


    public List<Element> getElementsByCategoryId(int id) {
        return remoteCategoryService.getElementsByCategoryId(id);
    }

    public List<CategoryType> getCategoryTypes() { return remoteCategoryService.getCategoryTypes(); }
    public List<Category> getCategoriesByType(int typeId) { return remoteCategoryService.getCategoriesByType(typeId); }

    public void removeCategory(Category category) {
        remoteCategoryService.deleteCategory(category);

    }

    public void removeElement(Element element) {
        remoteCategoryService.deleteElement(element);
    }
}
