package com.agh.soa.game.controller;

import com.agh.soa.*;
import remote.RemoteCategoryService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@ConversationScoped
@ManagedBean
public class CreateController implements Serializable {

    @EJB(mappedName = "java:global/server-1.0-SNAPSHOT/CategoryService!remote.RemoteCategoryService")
    private RemoteCategoryService remoteCategoryService;

    private List<Category> categories;
    private List<CategoryType> categoryTypes;
    private List<ElementType> elementTypes;
    private List<Element> elements;

    private Category category;
    private CategoryType categoryType;
    private ElementType elementType;
    private Element element;
    private Integer value;
    private String mode;
    @Inject
    private Conversation conversation;


    @PostConstruct
    public void initialize() {
            mode = getRequestParameter("mode");
            categoryTypes = remoteCategoryService.getCategoryTypes();
            categoryType = categoryTypes.get(0);
            categories = remoteCategoryService.getCategoriesByType(categoryType.getId());
            elementTypes = remoteCategoryService.getElementTypes();
            elementType = remoteCategoryService.getElementTypeById(categoryType.getId());
            elements = remoteCategoryService.getByElementType(elementType.getId());
            category = new Category();

    }

    public void initConversation(){
        if (!FacesContext.getCurrentInstance().isPostback() && conversation.isTransient()) {
            conversation.begin();
        }
    }

    public Conversation getConversation() {
        return conversation;
    }
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategoryById(int id){
        return categories.stream().filter(c -> c.getId()==id).findAny().get();
    }

    public CategoryType getCategoryTypeById(int id) {
        return categoryTypes.stream()
                .filter(categoryType -> categoryType.getId() == id).findAny().get();
    }

    public void updateElLabels() {
        element = new Element();
        categories = remoteCategoryService.getCategoriesByType(categoryType.getId());
        elementType = remoteCategoryService.getElementTypeById(categoryType.getId());

    }

    public void updateCategory() {
        categories = remoteCategoryService.getCategoriesByType(categoryType.getId());
        category = categories.get(0);
    }

    public void updateElements() {
        elements = remoteCategoryService.getByElementType(elementType.getId());
        element = elements.get(0);
    }


    public void createCategoryObject() throws IOException {
        Category categoryToPersist = new Category();
        categoryToPersist.setCategoryTypesByTypeId(categoryType);
        categoryToPersist.setParamValue(value);
        remoteCategoryService.createCategory(categoryToPersist);
        reload();
    }

    public void createElementObject() throws IOException {
        element.setCategoriesByCategoryId(category);
        element.setElementTypesByTypeId(elementType);
        remoteCategoryService.createElement(element);
        reload();
    }

    public void editCategoryObject() throws IOException {
        remoteCategoryService.editCategory(category);
        reload();
    }

    public void editElementObject() throws IOException {
        remoteCategoryService.editElement(element);
        reload();
    }

    public boolean isEditMode() {
        return "edit".equals(mode);
    }

    public boolean isCreateMode() {
        return "create".equals(mode);
    }


    private void reload() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    private String getRequestParameter(String parameterName) {
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        return requestParameterMap.get(parameterName);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<CategoryType> getCategoryTypes() {
        return categoryTypes;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public List<ElementType> getElementTypes() {
        return elementTypes;
    }


    public ElementType getElementType() {
        return elementType;
    }

    public void setElementType(ElementType elementType) {
        this.elementType = elementType;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public List<Element> getElements() {
        return elements;
    }
}

