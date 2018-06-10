package com.agh.soa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_roles", schema = "projekt_soa", catalog = "")
public class Role implements Serializable {
    private String login;
    private String role;

    @Id
    @Column(name = "login")
    public String getLogin() { return login;}

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return login == role1.login &&
                Objects.equals(role, role1.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(login, role);
    }
}
