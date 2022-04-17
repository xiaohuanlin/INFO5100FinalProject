/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finalproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Set;


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
    private Set<Role> roles = new HashSet();
    
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
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Permission> criteria = builder.createQuery(Permission.class);
        Root<Permission> root = criteria.from(Permission.class);
        Root<User> userRoot = criteria.from(User.class);
        Join<User, Role> roleJoin = root.join(User_.ROLES);
        Join<Permission, Role> permissionJoin = roleJoin.join(Role_.PERMISSIONS);
        
        criteria.select(root);
        criteria.where(
            builder.and(
                builder.equal(root.get(Permission_.NAME), name),
                builder.equal(root.get(Permission_.PERMISSION_TYPE), ptype),
                builder.equal(userRoot.get(User_.USERNAME), this.getUsername())
            )
        );
        return !entityManager.createQuery(criteria).getResultList().isEmpty();
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
