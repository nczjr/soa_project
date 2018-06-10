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

    private String label;

    private Integer value;

    @PostConstruct
    public void initialize() {
        categoryTypes = remoteCategoryService.getCategoryTypes();
        categoryType = categoryTypes.get(0);
        categories = remoteCategoryService.getAllCategories();
        labels = remoteCategoryService.getParametersLabelsByCategoryType(categoryType.getId());
        category = new Category();
        label = getLabel();
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

    public void updateLabel() {
        System.out.println("cipa");
        label = getLabel();
        category.setTypeId(categoryType.getId());

    }

    public void updateLabels(){
        labels = new ArrayList<>();
        parameterMap = new HashMap<>();
        labels = remoteCategoryService.getParametersLabelsByCategoryType(categoryType.getId());
        labels.forEach( s -> parameterMap.put((String) s, null));
        stringLabel = remoteCategoryService.getStringLabelByCategoryType(categoryType.getId());
        category.setTypeId(categoryType.getId());
        categories = remoteCategoryService.getCategoriesByType(categoryType.getId());
    }

    public String getLabel() {
        return remoteCategoryService.getLabelByCategoryTypeId(categoryType.getId());
    }

    public void createCategoryObject() {
        IntParameter parameter = new IntParameter();
        parameter.setCategoriesByCategoryId(category);
        parameter.setLabel(label);
        parameter.setValue(value);
        // @todo
        parameter.setUserId(1);
        remoteCategoryService.createCategoryByParameter(parameter);
    }

    public void createElementObject() {
        List<IntParameter> parameters = new ArrayList<>();
        Element element = new Element();
        element.setCategories(category);
        ElementType elementType = remoteCategoryService.getElementTypeById(categoryType.getId());
        element.setTypeId(elementType);
        for (String label : labels) {
            IntParameter param = new IntParameter();
            param.setValue(Integer.parseInt(parameterMap.get(label)));
            param.setElementByElementId(element);
            // @todo
            param.setUserId(1);
            param.setLabel(label);
            parameters.add(param);
        }
        StringParameter stringParameter = new StringParameter();
        stringParameter.setLabel(stringLabel);
        stringParameter.setValue(stringValue);
        stringParameter.setElementByElementId(element);
        // @todo
        stringParameter.setUserId(1);
        remoteCategoryService.createElementByParameters(parameters, stringParameter);
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
}

