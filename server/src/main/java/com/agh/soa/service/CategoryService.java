package com.agh.soa.service;

import com.agh.soa.*;
import com.agh.soa.dao.CategoryDAO;
import com.agh.soa.dao.ElementDAO;
import com.agh.soa.dao.UserDAO;
import com.agh.soa.interceptor.MethodInterceptor;
import remote.RemoteCategoryService;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.io.Serializable;
import java.security.Principal;
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
    private UserDAO userDAO;


    public List<CategoryType> getCategoryTypes() {
        return categoryDAO.findCategoryTypes();
    }

    @Override
    public List<ElementType> getElementTypes() {
        return elementDAO.findElementTypes();
    }

    public List<Category> getCategoriesByType(Integer typeId) {
        return  FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Administrator") ?
                categoryDAO.findByCategoryType(typeId)
                : categoryDAO.findByCategoryType(typeId).stream()
                                .filter(category -> category.getUser().getId() == getUserFromContext().getId())
                                .collect(Collectors.toList());
    }

    public List<Category> getAllCategoriesByType(Integer typeId) {
        return categoryDAO.findByCategoryType(typeId);
    }

    public List<Element> getElementsByCategoryId(Integer id) {
        return elementDAO.findByCategoryId(id);
    }

    public ElementType getElementTypeById(Integer id) {
        return elementDAO.findElemenTypeByTypeId(id);
    }

    public List<Element> getByElementType(Integer id) {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Administrator") ?
                elementDAO.findByElementType(id) : elementDAO.findByElementType(id).stream()
                                                    .filter(element -> element.getCategoriesByCategoryId().getUser().getId() == getUserFromContext().getId())
                                                    .collect(Collectors.toList());
    }

    public List<Element> getMostPowerfulElements() {
        return elementDAO.findMostPowerfulElements();
    }

    public void createCategory(Category category) {
        category.setUser(getUserFromContext());
        categoryDAO.create(category);
    }

    @Interceptors(MethodInterceptor.class)
    public void createElement(Element element) {
        elementDAO.create(element);
    }

    public void createCategoryType(CategoryType categoryType) {
        categoryDAO.createCategoryType(categoryType);
    }

    public void createElementType(ElementType elementType) {
        elementDAO.createElementType(elementType);
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

    public void editElementField(int elementId, int value) {
        Element el = elementDAO.findElementById(elementId);
        el.setIntValue1(value);
        elementDAO.edit(el);
    }

    private User getUserFromContext() {
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        String login = principal.getName();
        return userDAO.getUserByLogin(login);
    }

    public void changePassword(User user, String oldPassword, String newPassword) {
        if (userDAO.isValidPassword(user, oldPassword)) {
            user.setPasswd(newPassword);
            userDAO.edit(user);
        }
    }

    public List<User> getUsers() {
        return userDAO.getUsers();
    }
}
