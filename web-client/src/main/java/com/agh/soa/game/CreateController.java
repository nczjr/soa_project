package com.agh.soa.game;

import com.agh.soa.*;
import remote.RemoteCategoryService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
@ManagedBean
public class CreateController implements Serializable {

    @EJB(mappedName = "java:global/server-1.0-SNAPSHOT/CategoryService!remote.RemoteCategoryService")
    private RemoteCategoryService remoteCategoryService;

    private List<Category> categories;
    private List<String> labels;
    private List<CategoryType> categoryTypes;
    private List<ElementType> elementTypes;
    private Map<String, String> parameterMap;
    private String stringLabel;
    private String stringValue;

    private Category category;
    private CategoryType categoryType;
    private ElementType elementType;
    private Element element;

    private String label;

    private Integer value;

    @PostConstruct
    public void initialize() {
        categoryTypes = remoteCategoryService.getCategoryTypes();
        categoryType = categoryTypes.get(0);
        categories = remoteCategoryService.getAllCategories();
        category = new Category();
        parameterMap = new HashMap<>();
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

    public void updateLabels() {
        element = new Element();
        elementType = remoteCategoryService.getElementTypeById(categoryType.getId());
    }


    public void createCategoryObject() {
        Category categoryToPersist = new Category();
        categoryToPersist.setCategoryTypesByTypeId(categoryType);
        categoryToPersist.setParamValue(value);
        categoryToPersist.setUser(remoteCategoryService.findUserById(1));
        remoteCategoryService.createCategory(categoryToPersist);
    }

    public void createElementObject() {
        element.setCategoriesByCategoryId(category);
        element.setElementTypesByTypeId(elementType);
        remoteCategoryService.createElement(element);
    }


    public void removeCategory(Category category) {
        remoteCategoryService.deleteCategory(category);

    }

    public void removeElement(Element element) {
        remoteCategoryService.deleteElement(element);
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

    public List<String> getLabels() {
        return labels;
    }

    public Map<String, String> getParameterMap() {
        return parameterMap;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getStringLabel() {
        return stringLabel;
    }

    public void setStringLabel(String stringLabel) {
        this.stringLabel = stringLabel;
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

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }
}

