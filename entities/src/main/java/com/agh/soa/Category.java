package com.agh.soa;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "categories", schema = "projekt_soa", catalog = "")
public class Category implements Serializable {

    private Integer id;
    private Integer typeId;
    private IntParameter intParameters;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type_id")
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id &&
                Objects.equals(typeId, category.typeId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, typeId);
    }

    @OneToOne(mappedBy = "categoriesByCategoryId")
    public IntParameter getIntParameters() {
        return intParameters;
    }

    public void setIntParameters(IntParameter intParameters) {
        this.intParameters = intParameters;
    }

    @Override
    public String toString() {
        return "com.agh.soa.Category{" +
                "id=" + id +
                ", categoryType='" + typeId +
                '}';
    }
}
