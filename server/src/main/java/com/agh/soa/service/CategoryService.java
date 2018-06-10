package com.agh.soa.service;

import com.agh.soa.*;
import com.agh.soa.dao.CategoryDAO;
import com.agh.soa.dao.ElementDAO;
import com.agh.soa.dao.UserDAO;
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
    private EntityService entityService;

    @Inject
    private UserDAO userDAO;


    public List<CategoryType> getCategoryTypes() { return  categoryDAO.findCategoryTypes(); }

    @Override
    public List<ElementType> getElementTypes() {
        return elementDAO.findElementTypes();
    }

    public List<Category> getCategoriesByType(Integer typeId) {
        return categoryDAO.findByCategoryType(typeId);
    }

    public List<Element> getElements() {
        return elementDAO.findAll();
    }

    public List<Element> getElementsByCategoryId(Integer id) { return entityService.getElementsByCategoryId(id); }


    public ElementType getElementTypeById(Integer id) {
        return elementDAO.findElemenTypeByTypeId(id);
    }
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        for( CategoryType c : categoryDAO.findCategoryTypes()){
            categories.addAll(categoryDAO.findByCategoryType(c.getId()));
        }
        return categories;
    }

    public List<Element> getByElementType(Integer id) {
        return elementDAO.findByElementType(id);
    }

    public User findUserById(Integer id) {
        return userDAO.getUserById(id);
    }

    public void createCategory(Category category) {
        categoryDAO.create(category);
    }

    public void createElement(Element element) {
        elementDAO.create(element);
    }
    public void deleteCategory(Category category) {
        categoryDAO.deleteCategory(category);
    }

    public void deleteElement(Element element) {
        elementDAO.deleteElement(element);
    }

    public void editCategory(Category category) {
        categoryDAO.edit(category);
    }
    public void editElement(Element element) {
        elementDAO.edit(element);
    }
}
