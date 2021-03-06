/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finalproject.model;

/**
 *
 * @author Administrator
 */
public enum BusinessSourceType {
    ORDER("order"),
    REFUND_ORDER("refund order"),
    PURCHASE_ORDER("purchase order"),
    ;
    
    private final String text;

    BusinessSourceType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
