package com.agh.soa.game.controller;

import com.agh.soa.User;
import remote.RemoteCategoryService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Named
@SessionScoped
public class PasswordChangeController implements Serializable {

    @EJB(mappedName = "java:global/server-1.0-SNAPSHOT/CategoryService!remote.RemoteCategoryService")
    private RemoteCategoryService remoteCategoryService;

    private String oldPassword;
    private String newPassword;
    private List<User> users;
    private User user;


    @PostConstruct
    public void initialize() {
        users = remoteCategoryService.getUsers();

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        if (!externalContext.isUserInRole("Administrator")) {
            users = Collections.singletonList(users.stream()
                    .filter(user -> user.getLogin().equals(externalContext.getUserPrincipal().getName()))
                    .findFirst()
                    .get());
        }
        user = users.get(0);

    }

    public void changePassword() {
        remoteCategoryService.changePassword(user, oldPassword, newPassword);
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
