package com.agh.soa.service;

import com.agh.soa.Category;
import com.agh.soa.Element;
import com.agh.soa.dao.CategoryDAO;
import com.agh.soa.dao.ElementDAO;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
public class EntityService {


    @Inject
    CategoryDAO categoryDAO;
    @Inject
    ElementDAO elementDAO;

    public List<Category> getCategories() {
        return categoryDAO.findAll();
    }

    public List<Element> getElements() {
        return elementDAO.findAll();
    }

    public List<Element> getElementsByCategoryId(int id) { return elementDAO.findByCategoryId(id); }

}
