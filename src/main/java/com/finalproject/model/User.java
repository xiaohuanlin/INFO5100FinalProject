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
    public Set<Role> roles = new HashSet();
    
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
    
    public boolean hasPermission(String className, PermissionType ptype) {
        for (Role r: roles) {
            for (Permission p: r.permissions) {
                if (p.getClassName().equals(className)) {
                    switch (ptype) {
                        case VIEW:
                            if (p.isViewAbility()) {
                                return true;
                            }
                            break;
                        case EDIT:
                            if (p.isEditAbility()) {
                                return true;
                            }
                            break;
                        case DELETE:
                            if (p.isDeleteAbility()) {
                                return true;
                            }
                            break;
                        case CREATE:
                            if (p.isCreateAbility()) {
                                return true;
                            }
                            break;
                        default:
                            throw new AssertionError();
                    }
                }
            }
        }
        return false;
    }
        
    public static User findByUsernameAndPassword(String username, String password) {
        EntityManager entityManager =emf.createEntityManager();
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
}
