package com.agh.soa.game;

import com.agh.soa.*;
import remote.RemoteCategoryService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import java.util.List;

@Named
@ManagedBean
public class CategoriesController {

    @EJB(mappedName = "java:global/server-1.0-SNAPSHOT/CategoryService!remote.RemoteCategoryService")
    private RemoteCategoryService remoteCategoryService;

    private List<Category> categories;
    private Category category;
    private int value;

    @PostConstruct
    public void initialize() {
    }


    public void updateCategory(ValueChangeEvent e) {
        category = (Category) e.getNewValue();
    }
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getCategories() {
        return categories;
    }

//    public String getLabel() {
//        return category != null ? remoteCategoryServiceg(category.getId()) : "";
//    }
    public List<Element> getElementsByCategoryId(int id) {
        return remoteCategoryService.getElementsByCategoryId(id);
    }

    public List<IntParameter> getParametersByCategoryId(int id) {
        return remoteCategoryService.getParametersByCategoryId(id);
    }

    public List<IntParameter> getIntParametersByElementId(int id) { return remoteCategoryService.getIntParametersByElementId(id); }
    public List<StringParameter> getStringParametersByElementId(int id) { return remoteCategoryService.getStringParametersByElementId(id); }

    public List<CategoryType> getCategoryTypes() { return remoteCategoryService.getCategoryTypes(); }
    public List<Category> getCategoriesByType(int typeId) { return remoteCategoryService.getCategoriesByType(typeId); }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
