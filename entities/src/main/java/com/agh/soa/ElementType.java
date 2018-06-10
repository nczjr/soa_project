package com.agh.soa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "element_types", schema = "projekt_soa", catalog = "")
public class ElementType implements Serializable {
    private int id;
    private String elementType;
    private String intLabel1;
    private String intLabel2;
    private String nameLabel;
    private String powerLabel;

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

    @Basic
    @Column(name = "intLabel1")
    public String getIntLabel1() {
        return intLabel1;
    }

    public void setIntLabel1(String intLabel1) {
        this.intLabel1 = intLabel1;
    }

    @Basic
    @Column(name = "intLabel2")
    public String getIntLabel2() {
        return intLabel2;
    }

    public void setIntLabel2(String intLabel2) {
        this.intLabel2 = intLabel2;
    }

    @Basic
    @Column(name = "nameLabel")
    public String getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(String nameLabel) {
        this.nameLabel = nameLabel;
    }

    @Basic
    @Column(name = "powerLabel")
    public String getPowerLabel() {
        return powerLabel;
    }

    public void setPowerLabel(String powerLabel) {
        this.powerLabel = powerLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementType that = (ElementType) o;
        return id == that.id &&
                Objects.equals(elementType, that.elementType) &&
                Objects.equals(intLabel1, that.intLabel1) &&
                Objects.equals(intLabel2, that.intLabel2);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, elementType, intLabel1, intLabel2);
    }
}
