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
import jakarta.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;


/**
 *
 * @author Administrator
 */
@Entity
public class BusinessPurchaseOrder extends ORMObject {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    private BusinessProduct product;

    @Column(nullable = false)
    private Integer totalAmount;
    
    @Column(nullable = false)
    private BusinessPurchaseOrderStatusType orderStatusType = BusinessPurchaseOrderStatusType.INITIAL;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private LocalDateTime createDate;

    @Column(nullable = false)
    private LocalDateTime updateDate;

    @Column
    private String description;

    @ManyToOne
    private Enterprise enterprise;

    public Integer getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) throws Exception {
        if (quantity < 0) {
            throw new Exception("Quantity less than 0");
        }
        this.quantity = quantity;
    }

    public BusinessProduct getProduct() {
        return product;
    }

    public void setProduct(BusinessProduct product) {
        this.product = product;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) throws Exception {
        if (totalAmount < 0) {
            throw new Exception("TotalAmount less than 0");
        }
        this.totalAmount = totalAmount;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BusinessPurchaseOrderStatusType getOrderStatusType() {
        return orderStatusType;
    }

    public void setOrderStatusType(BusinessPurchaseOrderStatusType orderStatusType) {
        this.orderStatusType = orderStatusType;
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

    public static List<BusinessPurchaseOrder> find(String productEnterprise, String enterprise) {
        List<BusinessPurchaseOrder> res = BusinessPurchaseOrder.find(BusinessPurchaseOrder.class);
        return res.stream().filter( x -> {
            if (!enterprise.equals("") && !x.getEnterprise().getName().equals(enterprise)) {
                return false;
            }
            return x.getProduct().getEnterprise().getName().equals(productEnterprise);
        }).toList();
    }

    public static List<BusinessPurchaseOrder> findByDate(LocalDateTime start, LocalDateTime end) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BusinessPurchaseOrder> criteria = builder.createQuery(BusinessPurchaseOrder.class);
        Root<BusinessPurchaseOrder> root = criteria.from(BusinessPurchaseOrder.class);
        criteria.select(root);
        criteria.where(
            builder.and(
                builder.greaterThan(root.get(BusinessPurchaseOrder_.CREATE_DATE), start),
                builder.lessThan(root.get(BusinessPurchaseOrder_.CREATE_DATE), end)
            )
        );
        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public String toString() {
        return id + "(" + orderStatusType + ")";
    }
}
