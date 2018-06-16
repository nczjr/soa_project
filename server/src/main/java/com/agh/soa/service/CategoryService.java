package com.agh.soa.service;

import com.agh.soa.*;
import com.agh.soa.dao.CategoryDAO;
import com.agh.soa.dao.ElementDAO;
import com.agh.soa.dao.UserDAO;
import remote.RemoteCategoryService;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.security.Principal;
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
    private UserDAO userDAO;


    public List<CategoryType> getCategoryTypes() {
        return categoryDAO.findCategoryTypes();
    }

    @Override
    public List<ElementType> getElementTypes() {
        return elementDAO.findElementTypes();
    }

    public List<Category> getCategoriesByType(Integer typeId) {
        User user = getUserFromContext();
        Role role = userDAO.getUserRole(user.getLogin());
        return role.getRole().equals("Administrator") ? categoryDAO.findByCategoryType(typeId)
                : categoryDAO.findByCategoryType(typeId).stream()
                                .filter(category -> category.getUser().getId() == user.getId())
                                .collect(Collectors.toList());
    }

    public List<Element> getElementsByCategoryId(Integer id) {
        return elementDAO.findByCategoryId(id);
    }

    public ElementType getElementTypeById(Integer id) {
        return elementDAO.findElemenTypeByTypeId(id);
    }

    public List<Element> getByElementType(Integer id) {
        User user = getUserFromContext();
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Administrator") ?
                elementDAO.findByElementType(id) : elementDAO.findByElementType(id).stream()
                                                    .filter(element -> element.getCategoriesByCategoryId().getUser().getId() == user.getId())
                                                    .collect(Collectors.toList());
    }

    public void createCategory(Category category) {
        category.setUser(getUserFromContext());
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
