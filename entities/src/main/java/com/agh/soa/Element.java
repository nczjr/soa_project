package com.agh.soa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "elements", schema = "projekt_soa")
public class Element implements Serializable {

    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ElementType typeId;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Category categories;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return id == element.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
