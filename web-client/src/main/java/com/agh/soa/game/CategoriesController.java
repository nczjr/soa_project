package com.agh.soa.game;

import com.agh.soa.Category;
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
        categories = remoteCategoryService.getCategories();
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

    public List getParametersByCategory(int id) {
        return remoteCategoryService.getParameterByCategoryId(id);
    }

//    public String getLabel() {
//        return category != null ? remoteCategoryServiceg(category.getId()) : "";
//    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
