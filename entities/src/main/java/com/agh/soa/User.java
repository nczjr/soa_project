package com.agh.soa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "projekt_soa", catalog = "")
@NamedQuery(name = "findUserById", query = "SELECT OBJECT (u) from User u where u.id=:id")
public class User implements Serializable {
    private int id;
    private String username;
    private String passwd;
    private Role rolesByRoleId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "passwd")
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(username, user.username) &&
                Objects.equals(passwd, user.passwd);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, passwd);
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    public Role getRolesByRoleId() {
        return rolesByRoleId;
    }

    public void setRolesByRoleId(Role rolesByRoleId) {
        this.rolesByRoleId = rolesByRoleId;
    }
}
