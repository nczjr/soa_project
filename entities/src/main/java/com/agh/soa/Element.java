package com.agh.soa;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
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

    @OneToMany(mappedBy = "elementByElementId")
    private Collection<IntParameter> intParameters;

    @OneToMany(mappedBy = "elementByElementId")
    private Collection<StringParameter> stringParameters;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Collection<IntParameter> getIntParameters() {
        return intParameters;
    }

    public void setIntParameters(Collection<IntParameter> intParameters) {
        this.intParameters = intParameters;
    }

    public Collection<StringParameter> getStringParameters() {
        return stringParameters;
    }

    public void setStringParameters(Collection<StringParameter> stringParameters) {
        this.stringParameters = stringParameters;
    }

    public ElementType getTypeId() {
        return typeId;
    }

    public void setTypeId(ElementType typeId) {
        this.typeId = typeId;
    }

    public Category getCategories() {
        return categories;
    }

    public void setCategories(Category categories) {
        this.categories = categories;
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
