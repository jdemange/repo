/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

/**
 *
 * @author apprentice
 */
public class OrderId {
    private int idNumber;
    
    public OrderId(int idNumber){
        this.idNumber = idNumber;
    }
    
    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }
    
}
