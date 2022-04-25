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
public class BusinessDeliverOrder extends ORMObject {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String fromLocation;

    @Column(nullable = false)
    private String toLocation;

    @ManyToOne
    private BusinessVehicle businessVehicle;

    @ManyToOne
    private BusinessProduct businessProduct;

    @ManyToOne
    private Enterprise fromEnterprise;

    @Column(nullable = false)
    private Integer fee;
    
    @Column(nullable = false)
    private BusinessDeliverOrderStatusType orderStatusType = BusinessDeliverOrderStatusType.CREATE;

    @Column(nullable = false)
    private LocalDateTime createDate;

    @Column(nullable = false)
    private LocalDateTime updateDate;

    public Integer getId() {
        return id;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public Enterprise getFromEnterprise() {
        return fromEnterprise;
    }

    public void setFromEnterprise(Enterprise fromEnterprise) {
        this.fromEnterprise = fromEnterprise;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public BusinessVehicle getBusinessVehicle() {
        return businessVehicle;
    }

    public void setBusinessVehicle(BusinessVehicle businessVehicle) {
        this.businessVehicle = businessVehicle;
    }

    public BusinessProduct getBusinessProduct() {
        return businessProduct;
    }

    public void setBusinessProduct(BusinessProduct businessProduct) {
        this.businessProduct = businessProduct;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public BusinessDeliverOrderStatusType getOrderStatusType() {
        return orderStatusType;
    }

    public void setOrderStatusType(BusinessDeliverOrderStatusType orderStatusType) {
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

    @Override
    public String toString() {
        return id + "(" + orderStatusType + ")";
    }
}
