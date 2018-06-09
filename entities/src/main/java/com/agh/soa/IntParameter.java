package com.agh.soa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "int_parameters", schema = "projekt_soa", catalog = "")
@NamedQuery(
        name="findByCategoryId",
        query="SELECT OBJECT(p) FROM IntParameter p where p.categoryId=:id and p.value is not null"
)
public class IntParameter implements Serializable {

    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "label")
    private String label;

    @Basic
    @Column(name = "value")
    private Integer value;

    @Basic
    @Column(name = "category_id")
    private Integer categoryId;

    @Basic
    @Column(name = "element_id")
    private Integer elementId;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Category categoriesByCategoryId;

    @ManyToOne
    @JoinColumn(name = "element_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Element elementByElementId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User usersByUserId;

    @Basic
    @Column(name = "user_id")
    private int userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

   public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getElementId() {
        return elementId;
    }

    public void setElementId(Integer elementId) {
        this.elementId = elementId;
    }

    public Category getCategoriesByCategoryId() {
        return categoriesByCategoryId;
    }

    public void setCategoriesByCategoryId(Category categoriesByCategoryId) {
        this.categoriesByCategoryId = categoriesByCategoryId;
    }

    public Element getElementByElementId() {
        return elementByElementId;
    }

    public void setElementByElementId(Element elementByElementId) {
        this.elementByElementId = elementByElementId;
    }

    public User getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(User usersByUserId) {
        this.usersByUserId = usersByUserId;
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

}
