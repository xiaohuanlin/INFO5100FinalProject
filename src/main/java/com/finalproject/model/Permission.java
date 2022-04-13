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
import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author Administrator
 */
@Entity
public class Permission extends ORMObject {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String className;
    
    private boolean viewAbility = false;
    
    private boolean editAbility = false;
    
    private boolean createAbility = false;
    
    private boolean deleteAbility = false;
    
    @ManyToMany(targetEntity=Role.class, mappedBy="permissions")
    public Set<Role> roles = new HashSet();

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean isViewAbility() {
        return viewAbility;
    }

    public void setViewAbility(boolean viewAbility) {
        this.viewAbility = viewAbility;
    }

    public boolean isEditAbility() {
        return editAbility;
    }

    public void setEditAbility(boolean editAbility) {
        this.editAbility = editAbility;
    }

    public boolean isCreateAbility() {
        return createAbility;
    }

    public void setCreateAbility(boolean createAbility) {
        this.createAbility = createAbility;
    }

    public boolean isDeleteAbility() {
        return deleteAbility;
    }

    public void setDeleteAbility(boolean deleteAbility) {
        this.deleteAbility = deleteAbility;
    }

    
}
