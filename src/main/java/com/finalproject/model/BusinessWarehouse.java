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
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;


/**
 *
 * @author Administrator
 */
@Entity
public class BusinessWarehouse extends ORMObject {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer totalQuantity;

    @Column(nullable = false)
    private String location;
    
    @Column(nullable = false)
    private LocalDateTime createDate;

    @Column(nullable = false)
    private LocalDateTime updateDate;

    public Integer getId() {
        return id;
    }

    public Integer getAvailableQuantity() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BusinessWarehouseEntry> criteria = builder.createQuery(BusinessWarehouseEntry.class);
        Root<BusinessWarehouseEntry> root = criteria.from(BusinessWarehouseEntry.class);
        criteria.select(root);
        criteria.where(
            builder.equal(root.get(BusinessWarehouseEntry_.BUSINESS_WAREHOUSE), this)
        );
        List<BusinessWarehouseEntry> list = entityManager.createQuery(criteria).getResultList();
        return totalQuantity - list.stream().map(entry -> entry.getQuantity()).mapToInt(i -> i).sum();
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }


    @PrePersist
    protected void onCreate() {
        updateDate = LocalDateTime.now();
        createDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updateDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return id + "(" + location + ":" + getAvailableQuantity() + ")";
    }
}
