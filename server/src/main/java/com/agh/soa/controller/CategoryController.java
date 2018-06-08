package com.agh.soa.controller;

import com.agh.soa.dao.CategoryDAO;
import com.agh.soa.dao.IntParametersDAO;
import com.agh.soa.entity.Category;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ConversationScoped
public class CategoryController implements Serializable {

    @Inject
    CategoryDAO categoryDAO;

    @Inject
    IntParametersDAO intParametersDAO;

    private Category category;
    private List<Category> categories;
    private String label;
    private int value;

    public CategoryController() {
    }

    @PostConstruct
    public void initialize() {
        categories = categoryDAO.findAll();
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

    public String getLabel() {
        return category != null ? intParametersDAO.findParameterLabelByCategoryId(category.getId()) : "";
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
