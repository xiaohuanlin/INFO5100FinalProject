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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
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
    private Integer purchasePrice;

    @Column(nullable = false)
    private Integer sellPrice;

    @Column(nullable = false)
    private BusinessProductStatusType productStatusType = BusinessProductStatusType.AVAILABLE;

    @ManyToOne
    private Enterprise enterprise;

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

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Integer purchasePrice) throws Exception {
        if (purchasePrice < 0) {
            throw new Exception("Price less than 0");
        }
        this.purchasePrice = purchasePrice;
    }

    public Integer getSellPrice() {
        return sellPrice;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public void setSellPrice(Integer sellPrice) throws Exception {
        if (sellPrice < 0) {
            throw new Exception("Price less than 0");
        }
        this.sellPrice = sellPrice;
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

    public static List<BusinessProduct> findAvailableProduct(String organizationName) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BusinessProduct> criteria = builder.createQuery(BusinessProduct.class);
        Root<BusinessProduct> root = criteria.from(BusinessProduct.class);
        Join<Enterprise, BusinessProduct> rootJoin = root.join(BusinessProduct_.ENTERPRISE);
        criteria.select(root);
        criteria.where(
            builder.and(
                builder.equal(root.get(BusinessProduct_.PRODUCT_STATUS_TYPE), BusinessProductStatusType.AVAILABLE),
                builder.equal(rootJoin.get(Enterprise_.NAME), organizationName)
            )
        );
        return entityManager.createQuery(criteria).getResultList();
    }

    public static List<BusinessProduct> findProducts(String organizationName) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BusinessProduct> criteria = builder.createQuery(BusinessProduct.class);
        Root<BusinessProduct> root = criteria.from(BusinessProduct.class);
        Join<Enterprise, BusinessProduct> rootJoin = root.join(BusinessProduct_.ENTERPRISE);
        criteria.select(root);
        if (!organizationName.equals("")) {
            criteria.where(
                builder.equal(rootJoin.get(Enterprise_.NAME), organizationName)
            );
        }
        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public String toString() {
        return name + "(" + productStatusType + ")";
    }
}
