package com.agh.soa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Element_types", schema = "projekt_soa", catalog = "")
public class ElementType implements Serializable {

    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "element_type")
    private String elementType;

   public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
