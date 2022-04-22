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
public class BusinessProduct extends ORMObject {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;
    
    @Column(nullable = false)
    private BusinessProductStatusType productStatusType = BusinessProductStatusType.AVAILABLE;

    @Column(nullable = false)
    private LocalDateTime createDate;

    @Column(nullable = false)
    private LocalDateTime updateDate;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public BusinessProductStatusType getProductStatusType() {
        return productStatusType;
    }

    public void setProductStatusType(BusinessProductStatusType productStatusType) {
        this.productStatusType = productStatusType;
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

    public static List<BusinessProduct> findAvailableProduct() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BusinessProduct> criteria = builder.createQuery(BusinessProduct.class);
        Root<BusinessProduct> root = criteria.from(BusinessProduct.class);
        criteria.select(root);
        criteria.where(
            builder.equal(root.get(BusinessProduct_.PRODUCT_STATUS_TYPE), BusinessProductStatusType.AVAILABLE)
        );
        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public String toString() {
        return id + "(" + productStatusType + ")";
    }
}
