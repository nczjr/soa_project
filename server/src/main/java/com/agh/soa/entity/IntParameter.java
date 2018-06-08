package com.agh.soa.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "int_parameters", schema = "projekt_soa", catalog = "")
@NamedQuery(
        name="findByCategoryId",
        query="SELECT OBJECT(p) FROM IntParameter p where p.categoryId=:id and p.value is not null"
)
public class IntParameter {
    private int id;
    private String label;
    private Integer value;
    private Integer categoryId;
    private Integer elementId;
    private Category categoriesByCategoryId;
    private Elements elementsByElementId;
    private User usersByUserId;
    private int userId;

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "label")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Basic
    @Column(name = "value")
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Basic
    @Column(name = "category_id")
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "element_id")
    public Integer getElementId() {
        return elementId;
    }

    public void setElementId(Integer elementId) {
        this.elementId = elementId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntParameter that = (IntParameter) o;
        return id == that.id &&
                Objects.equals(label, that.label) &&
                Objects.equals(value, that.value) &&
                Objects.equals(categoryId, that.categoryId) &&
                Objects.equals(elementId, that.elementId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, label, value, categoryId, elementId);
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Category getCategoriesByCategoryId() {
        return categoriesByCategoryId;
    }

    public void setCategoriesByCategoryId(Category categoriesByCategoryId) {
        this.categoriesByCategoryId = categoriesByCategoryId;
    }

    @ManyToOne
    @JoinColumn(name = "element_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Elements getElementsByElementId() {
        return elementsByElementId;
    }

    public void setElementsByElementId(Elements elementsByElementId) {
        this.elementsByElementId = elementsByElementId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    public User getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(User usersByUserId) {
        this.usersByUserId = usersByUserId;
    }
}
