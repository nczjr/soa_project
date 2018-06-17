package com.agh.soa;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categories", schema = "projekt_soa", catalog = "")
public class Category implements Serializable {
    private int id;
    private Integer paramValue;
    private CategoryType categoryTypesByTypeId;
    private User user;
    private List<Element> elementsByCategoryId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "paramValue")
    public Integer getParamValue() {
        return paramValue;
    }

    public void setParamValue(Integer paramValue) {
        this.paramValue = paramValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id &&
                Objects.equals(paramValue, category.paramValue);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, paramValue);
    }

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    public CategoryType getCategoryTypesByTypeId() {
        return categoryTypesByTypeId;
    }

    public void setCategoryTypesByTypeId(CategoryType categoryTypesByTypeId) {
        this.categoryTypesByTypeId = categoryTypesByTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "categoriesByCategoryId", cascade = CascadeType.REMOVE)
    public List<Element> getElementsByCategoryId() {
        return elementsByCategoryId;
    }


    public void setElementsByCategoryId(List<Element> elementsByCategoryId) {
        this.elementsByCategoryId = elementsByCategoryId;
    }

    @Override
    public String toString() {
        return "Category{" +
                "paramValue=" + paramValue +
                '}';
    }
}
