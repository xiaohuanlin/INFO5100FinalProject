/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finalproject.model;

/**
 *
 * @author Administrator
 */
public enum BusinessProductStatusType {
    AVAILABLE("available"),
    NOTAVAILABLE("not available"),
    ;
    
    private final String text;

    BusinessProductStatusType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
