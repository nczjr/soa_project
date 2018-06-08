package com.agh.soa.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Element_types", schema = "projekt_soa", catalog = "")
public class ElementType {
    private int id;
    private String elementType;
    private Collection<IntParameter> intParameters;
    private Collection<StringParameter> stringParameters;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "element_type")
    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementType elementType = (ElementType) o;
        return id == elementType.id &&
                Objects.equals(this.elementType, elementType.elementType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, elementType);
    }


}
