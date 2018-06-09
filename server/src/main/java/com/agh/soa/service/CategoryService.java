package com.agh.soa.service;

import com.agh.soa.Category;
import com.agh.soa.Element;
import com.agh.soa.dao.CategoryDAO;
import com.agh.soa.dao.ElementDAO;
import com.agh.soa.dao.IntParametersDAO;
import remote.RemoteCategoryService;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Remote(RemoteCategoryService.class)
@Stateless
public class CategoryService implements RemoteCategoryService, Serializable {


    @Inject
    private CategoryDAO categoryDAO;

    @Inject
    private ElementDAO elementDAO;

    @Inject
    private IntParametersDAO intParametersDAO;


    public List<Category> getCategories() {
        return categoryDAO.findAll();
    }

    public List<Element> getElements() {
        return elementDAO.findAll();
    }

    public List getParameterByCategoryId(int id) {
        return intParametersDAO.findByCategoryId(id);
    }



}
