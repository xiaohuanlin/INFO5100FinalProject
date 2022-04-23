/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finalproject.model;

/**
 *
 * @author Administrator
 */
public enum BusinessPurchaseOrderStatusType {
    INITIAL("initial"),
    PASS("pass"),
    RETURN("return"),
    ;
    
    private final String text;

    BusinessPurchaseOrderStatusType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
