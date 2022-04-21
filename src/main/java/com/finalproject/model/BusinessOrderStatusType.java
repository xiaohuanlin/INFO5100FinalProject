/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finalproject.model;

/**
 *
 * @author Administrator
 */
public enum BusinessOrderStatusType {
    CREATE("create"),
    SHIPPING("shipping"),
    SHIPPED("shipped"),
    REFUND("refund"),
    ;
    
    private final String text;

    BusinessOrderStatusType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
