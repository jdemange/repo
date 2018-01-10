/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 *
 * @author apprentice
 */
public class Order {
    private OrderId orderNumber;
    private String customerName;
    private StateTaxRate stateAndTaxRate;
    private Product productInfo;
    private BigDecimal area;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal taxCost;
    private BigDecimal totalCost;
    
    private LocalDate orderDate;

    public Order(OrderId orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    public OrderId getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(OrderId orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public StateTaxRate getStateAndTaxRate() {
        return stateAndTaxRate;
    }

    public void setStateAndTaxRate(StateTaxRate stateAndTaxRate) {
        this.stateAndTaxRate = stateAndTaxRate;
    }

    public Product getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(Product productInfo) {
        this.productInfo = productInfo;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTaxCost() {
        return taxCost;
    }

    public void setTaxCost(BigDecimal taxCost) {
        this.taxCost = taxCost;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
    
        @Override
    public String toString(){
        return "Order Id: " + orderNumber.getIdNumber() +  " |Customer Name: " + customerName +
                " |Total Price: $" + totalCost.setScale(2, RoundingMode.HALF_UP);
    }
    
}
