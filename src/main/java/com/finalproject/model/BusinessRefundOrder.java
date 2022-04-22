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
import java.time.LocalDateTime;


/**
 *
 * @author Administrator
 */
@Entity
public class BusinessRefundOrder extends ORMObject {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    private BusinessOrder order;

    @Column(nullable = false)
    private Integer amount;
    
    @Column(nullable = false)
    private BusinessRefundOrderStatusType businessRefundOrderStatusType = BusinessRefundOrderStatusType.CREATE;

    @Column(nullable = false)
    private LocalDateTime createDate;

    @Column(nullable = false)
    private LocalDateTime updateDate;

    public Integer getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) throws Exception {
        int total = order.getRefundOrders().stream().mapToInt((value) -> {
                return value.getAmount(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/LambdaBody
            }
        ).sum();
        
        if (total + (amount - (this.amount == null ? this.amount: 0)) > order.getTotalAmount()) {
            throw new Exception("Refund amount exceed order amount");
        }
        this.amount = amount;
    }

    public BusinessOrder getOrder() {
        return order;
    }

    public void setOrder(BusinessOrder order) {
        this.order = order;
    }

    public BusinessRefundOrderStatusType getBusinessRefundOrderStatusType() {
        return businessRefundOrderStatusType;
    }

    public void setBusinessRefundOrderStatusType(BusinessRefundOrderStatusType businessRefundOrderStatusType) {
        this.businessRefundOrderStatusType = businessRefundOrderStatusType;
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
        return id + "(" + businessRefundOrderStatusType + ")";
    }
}
