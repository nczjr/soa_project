package com.agh.soa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "category_types", schema = "projekt_soa", catalog = "")
@NamedQuery(name = "getCategoryTypes", query = "SELECT OBJECT (o) from CategoryType o")
public class CategoryType implements Serializable {
    private Integer id;
    private String name;
    private String paramName;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "paramName")
    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryType that = (CategoryType) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(paramName, that.paramName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, paramName);
    }

    @Override
    public String toString() {
        return "CategoryType{" +
                "name='" + name + '\'' +
                '}';
    }
}
