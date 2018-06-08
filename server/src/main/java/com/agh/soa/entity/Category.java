package com.agh.soa.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "categories", schema = "projekt_soa", catalog = "")
public class Category {
    private int id;
    private String categoryType;
    private Collection<IntParameter> intParameters;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "category_type")
    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id &&
                Objects.equals(categoryType, category.categoryType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, categoryType);
    }

    @OneToMany(mappedBy = "categoriesByCategoryId")
    public Collection<IntParameter> getIntParameters() {
        return intParameters;
    }

    public void setIntParameters(Collection<IntParameter> intParameters) {
        this.intParameters = intParameters;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryType='" + categoryType +
                '}';
    }
}
