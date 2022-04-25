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
public class BusinessVehicle extends ORMObject {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private BusinessVehicleType vehicleType = BusinessVehicleType.TRUCK;

    @Column(nullable = false)
    private BusinessVehicleStatusType vehicleStatusType = BusinessVehicleStatusType.AVAILABLE;

    @Column(nullable = false)
    private LocalDateTime createDate;

    @Column(nullable = false)
    private LocalDateTime updateDate;

    public Integer getId() {
        return id;
    }

    public BusinessVehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(BusinessVehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public BusinessVehicleStatusType getVehicleStatusType() {
        return vehicleStatusType;
    }

    public void setVehicleStatusType(BusinessVehicleStatusType vehicleStatusType) {
        this.vehicleStatusType = vehicleStatusType;
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

    public static List<BusinessVehicle> findAvailableVehicle() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BusinessVehicle> criteria = builder.createQuery(BusinessVehicle.class);
        Root<BusinessVehicle> root = criteria.from(BusinessVehicle.class);
        criteria.select(root);
        criteria.where(
            builder.equal(root.get(BusinessVehicle_.VEHICLE_STATUS_TYPE), BusinessVehicleStatusType.AVAILABLE)
        );
        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public String toString() {
        return id + "(" + vehicleType + ")";
    }
}
