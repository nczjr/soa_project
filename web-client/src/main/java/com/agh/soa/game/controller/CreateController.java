package com.agh.soa.game.controller;

import com.agh.soa.Category;
import com.agh.soa.CategoryType;
import com.agh.soa.Element;
import com.agh.soa.ElementType;
import com.agh.soa.game.event.ElementChangeEvent;
import remote.RemoteCategoryService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
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
    @Inject
    private Event<ElementChangeEvent> elementChangeEventEvent;


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

    public void closeConversation(){
        conversation.end();
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
        if (isEditMode())
            category = categories.get(0);
    }

    public void updateElements() {
        elements = remoteCategoryService.getByElementType(elementType.getId());
        if (isEditMode())
            element = elements.get(0);
    }


    public void createCategoryObject() throws IOException {
        Category categoryToPersist = category;
        categoryToPersist.setCategoryTypesByTypeId(categoryType);
        remoteCategoryService.createCategory(categoryToPersist);
    }

    public void createElementObject() throws IOException {
        element.setCategoriesByCategoryId(category);
        element.setElementTypesByTypeId(elementType);
        remoteCategoryService.createElement(element);
        fireElementEvent();
    }

    public void editCategoryObject() {
        remoteCategoryService.editCategory(category);
    }

    public void editElementObject() {
        remoteCategoryService.editElement(element);
        fireElementEvent();
    }

    public boolean isEditMode() {
        return "edit".equals(mode);
    }

    public boolean isCreateMode() {
        return "create".equals(mode);
    }

    private void fireElementEvent() {
        ElementChangeEvent event = new ElementChangeEvent();
        elementChangeEventEvent.fire(event);
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

