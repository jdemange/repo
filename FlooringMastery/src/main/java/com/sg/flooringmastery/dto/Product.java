/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author apprentice
 */
public class Product {
    private String productType;
    private BigDecimal materialCostPerSQFT;
    private BigDecimal laborCostPerSQFT;

    public Product(String productType) {
        this.productType = productType;
    }
    
    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getMaterialCostPerSQFT() {
        return materialCostPerSQFT;
    }

    public void setMaterialCostPerSQFT(BigDecimal materialCostPerSQFT) {
        this.materialCostPerSQFT = materialCostPerSQFT;
    }

    public BigDecimal getLaborCostPerSQFT() {
        return laborCostPerSQFT;
    }

    public void setLaborCostPerSQFT(BigDecimal laborCostPerSQFT) {
        this.laborCostPerSQFT = laborCostPerSQFT;
    }
    
}
