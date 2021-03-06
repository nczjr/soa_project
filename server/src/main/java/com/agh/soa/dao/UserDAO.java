package com.agh.soa.dao;

import com.agh.soa.Role;
import com.agh.soa.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

@Named
@ApplicationScoped
public class UserDAO {


    @Inject
    EntityManager entityManager;

    public UserDAO() {
    }

    public User getUserByLogin(String login) {
        Query query = entityManager.createNamedQuery("findUserByLogin");
        query.setParameter("login", login);
        return (User) query.getSingleResult();
    }

    public Role getUserRole(String login) {
        Query query = entityManager.createQuery("FROM Role r where r.login=:login");
        query.setParameter("login", login);
        return (Role) query.getSingleResult();
    }

    public List<User> getUsers() {
        Query query = entityManager.createQuery("FROM User ", User.class);
        return query.getResultList();
    }

    public boolean isValidPassword(User user, String password) {
        try {
            return user.getPasswd().equals(hash(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void edit(User user) {
        try {
            user.setPasswd(hash(user.getPasswd()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    private static String hash(String passwd) throws NoSuchAlgorithmException {
        MessageDigest md = null;
        md = MessageDigest.getInstance("MD5");
        byte[] passwordBytes = passwd.getBytes();
        byte[] hash = md.digest(passwordBytes);
        return Base64.getEncoder().encodeToString(hash);
    }
}
