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
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Administrator
 */
@Entity
public class BusinessOrder extends ORMObject {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    private BusinessProduct product;

    @Column(nullable = false)
    private Integer totalAmount;
    
    @Column(nullable = false)
    private BusinessOrderStatusType orderStatusType = BusinessOrderStatusType.CREATE;

    @Column(nullable = false)
    private LocalDateTime createDate;

    @Column(nullable = false)
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BusinessRefundOrder> refundOrders = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BusinessProduct getProduct() {
        return product;
    }

    public void setProduct(BusinessProduct product) {
        this.product = product;
    }
    
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<BusinessRefundOrder> getRefundOrders() {
        return refundOrders;
    }

    public void setRefundOrders(List<BusinessRefundOrder> refundOrders) {
        this.refundOrders = refundOrders;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BusinessOrderStatusType getOrderStatusType() {
        return orderStatusType;
    }

    public void setOrderStatusType(BusinessOrderStatusType orderStatusType) {
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
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

    public static List<BusinessOrder> findByDate(LocalDateTime start, LocalDateTime end) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BusinessOrder> criteria = builder.createQuery(BusinessOrder.class);
        Root<BusinessOrder> root = criteria.from(BusinessOrder.class);
        criteria.select(root);
        criteria.where(
            builder.and(
                builder.greaterThan(root.get(BusinessOrder_.CREATE_DATE), start),
                builder.lessThan(root.get(BusinessOrder_.CREATE_DATE), end)
            )
        );
        try {
            System.out.println(entityManager.createQuery(criteria).getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public String toString() {
        return id + "(" + orderStatusType + ")";
    }
}
