package com.agh.soa;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categories", schema = "projekt_soa", catalog = "")
public class Category {
    private int id;
    private int typeId;

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
                typeId == category.typeId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, typeId);
    }
}
