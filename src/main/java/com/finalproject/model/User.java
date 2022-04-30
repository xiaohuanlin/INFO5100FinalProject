/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finalproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Administrator
 */
@Entity
public class User extends ORMObject {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;

    @ManyToMany(targetEntity=Role.class)
    private List<Role> roles = new ArrayList<>();
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws Exception {
        if (!username.matches("^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}")) {
            throw new Exception("Email format error");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean hasPermission(String name, PermissionType ptype) {
        for (Role r: getRoles()) {
            for (Permission p: r.getPermissions()) {
                if (p.getName().equals(name) && p.getPermissionType() == ptype) {
                    return true;
                }
            }
        }
        return false;
    }
        
    public static User findByUsernameAndPassword(String username, String password) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);
        criteria.where(
            builder.and(
                builder.equal(root.get(User_.password), password),
                builder.equal(root.get(User_.username), username)
            )
        );
        return entityManager.createQuery(criteria).getSingleResult();
    }

    public Role findRole(String roleName) {
        for (Role r: roles) {
            if (r.getName().equals(roleName)) {
                return r;
            }
        }
        return null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return username;
    }
}
