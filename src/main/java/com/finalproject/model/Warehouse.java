/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finalproject.model;

/**
 *
 * @author jiaweiqian
 */
public class Warehouse {
    
    private String location;
    private String name;
    private int inStoreTime;
    private int outStoreTime;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInStoreTime() {
        return inStoreTime;
    }

    public void setInStoreTime(int inStoreTime) {
        this.inStoreTime = inStoreTime;
    }

    public int getOutStoreTime() {
        return outStoreTime;
    }

    public void setOutStoreTime(int outStoreTime) {
        this.outStoreTime = outStoreTime;
    }
    
    
}
