package com.agh.soa.service;

import com.agh.soa.*;
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
    @Inject
    private EntityService entityService;


    public List<CategoryType> getCategoryTypes() { return  categoryDAO.findCategoryTypes(); }

    public List<Category> getCategoriesByType(int typeId) {
        return categoryDAO.findByCategoryType(typeId);
    }

    public List<Element> getElements() {
        return elementDAO.findAll();
    }

    public List<IntParameter> getParametersByCategoryId(int id) { return entityService.getParametersByCategoryId(id); }

    public List<Element> getElementsByCategoryId(int id) { return entityService.getElementsByCategoryId(id); }

    public List<IntParameter> getIntParametersByElementId(int id) { return intParametersDAO.findIntParametersByElementId(id); }

    public List<StringParameter> getStringParametersByElementId(int id) { return intParametersDAO.findStringParametersByElementId(id); }


}
