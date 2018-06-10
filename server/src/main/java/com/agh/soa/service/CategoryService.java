package com.agh.soa.service;

import com.agh.soa.*;
import com.agh.soa.dao.CategoryDAO;
import com.agh.soa.dao.ElementDAO;
import com.agh.soa.dao.ParametersDAO;
import remote.RemoteCategoryService;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Remote(RemoteCategoryService.class)
@Stateless
public class CategoryService implements RemoteCategoryService, Serializable {


    @Inject
    private CategoryDAO categoryDAO;

    @Inject
    private ElementDAO elementDAO;

    @Inject
    private ParametersDAO parametersDAO;

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

    public List<IntParameter> getIntParametersByElementId(int id) { return parametersDAO.findIntParametersByElementId(id); }

    public List<StringParameter> getStringParametersByElementId(int id) { return parametersDAO.findStringParametersByElementId(id); }

    public void createCategoryByParameter(IntParameter parameter) {
       parametersDAO.create(parameter);
    }

    public void createElementByParameters(List<IntParameter> parameters, StringParameter stringParameter) {
        parameters.forEach(parametersDAO::create);
        parametersDAO.createStringParameter(stringParameter);

    }

    public ElementType getElementTypeById(int id) {
        return elementDAO.findElemenTypeByTypeId(id);
    }
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        for( CategoryType c : categoryDAO.findCategoryTypes()){
            categories.addAll(categoryDAO.findByCategoryType(c.getId()));
        }
        return categories;
    }

    public String getLabelByCategoryTypeId(int id) {
        int categoryId = categoryDAO.findByCategoryType(id).get(0).getId();
        return parametersDAO.findByCategoryId(categoryId).get(0).getLabel();
    }

    public String getStringLabelByCategoryType(int id) {
        int elementId = elementDAO.findByElementType(id).get(0).getId();
        return parametersDAO.findStringParametersByElementId(elementId).get(0).getLabel();
    }
    public List<String> getParametersLabelsByCategoryType(int id) {
        int elementId = elementDAO.findByElementType(id).get(0).getId();
        return parametersDAO.findParametersLabelsByElementId(elementId)
                .stream()
                .map(IntParameter::getLabel).collect(Collectors.toList());
    }
}
