package com.agh.soa.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "string_parameters", schema = "projekt_soa", catalog = "")
public class StringParameter {
    private int id;
    private String label;
    private String value;
    private Integer elementId;
    private Integer userId;
    private Elements elementsByElementId;
    private User usersByUserId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "label")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Basic
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "element_id")
    public Integer getElementId() {
        return elementId;
    }

    public void setElementId(Integer elementId) {
        this.elementId = elementId;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringParameter that = (StringParameter) o;
        return id == that.id &&
                Objects.equals(label, that.label) &&
                Objects.equals(value, that.value) &&
                Objects.equals(elementId, that.elementId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, label, value, elementId, userId);
    }

    @ManyToOne
    @JoinColumn(name = "element_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Elements getElementsByElementId() {
        return elementsByElementId;
    }

    public void setElementsByElementId(Elements elementsByElementId) {
        this.elementsByElementId = elementsByElementId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    public User getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(User usersByUserId) {
        this.usersByUserId = usersByUserId;
    }
}
