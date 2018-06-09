package com.agh.soa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "categories", schema = "projekt_soa", catalog = "")
public class Category implements Serializable {
    private int id;
    private int typeId;
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
    @Column(name = "type_id")
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
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


    @OneToMany(mappedBy = "categoriesByCategoryId")
    public Collection<IntParameter> getIntParameters() {
        return intParameters;
    }

    public void setIntParameters(Collection<IntParameter> intParameters) {
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
