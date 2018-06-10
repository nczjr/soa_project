package com.agh.soa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "elements", schema = "projekt_soa", catalog = "")
public class Element implements Serializable {
    private int id;
    private String name;
    private Integer intValue1;
    private Integer intValue2;
    private Integer powerValue;
    private ElementType elementTypesByTypeId;
    private Category categoriesByCategoryId;

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "intValue1")
    public Integer getIntValue1() {
        return intValue1;
    }

    public void setIntValue1(Integer intValue1) {
        this.intValue1 = intValue1;
    }

    @Basic
    @Column(name = "intValue2")
    public Integer getIntValue2() {
        return intValue2;
    }

    public void setIntValue2(Integer intValue2) {
        this.intValue2 = intValue2;
    }

    @Basic
    @Column(name = "powerValue")
    public Integer getPowerValue() {
        return powerValue;
    }

    public void setPowerValue(Integer powerValue) {
        this.powerValue = powerValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return id == element.id &&
                Objects.equals(name, element.name) &&
                Objects.equals(intValue1, element.intValue1) &&
                Objects.equals(intValue2, element.intValue2);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, intValue1, intValue2);
    }

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    public ElementType getElementTypesByTypeId() {
        return elementTypesByTypeId;
    }

    public void setElementTypesByTypeId(ElementType elementTypesByTypeId) {
        this.elementTypesByTypeId = elementTypesByTypeId;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    public Category getCategoriesByCategoryId() {
        return categoriesByCategoryId;
    }

    public void setCategoriesByCategoryId(Category categoriesByCategoryId) {
        this.categoriesByCategoryId = categoriesByCategoryId;
    }

    @Override
    public String toString() {
        return "Element{" +
                "name='" + name + '\'' +
                ", intValue1=" + intValue1 +
                ", intValue2=" + intValue2 +
                ", powerValue=" + powerValue +
                '}';
    }
}
