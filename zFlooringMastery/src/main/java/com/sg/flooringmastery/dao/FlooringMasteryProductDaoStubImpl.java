/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryProductDaoStubImpl implements FlooringMasteryProductDao {
    List<Product> productList = new ArrayList<>();
    
  

    @Override
    public List<Product> retrieveProductList(String currentFile) throws FlooringMasteryPersistenceException {
        Product prodA = new Product("Wood");
        prodA.setProductType("Wood");
        prodA.setMaterialCostPerSQFT(new BigDecimal(3.50));
        prodA.setLaborCostPerSQFT(new BigDecimal(4.67));
        
        productList.add(prodA);
        
        return productList;
    }
    
}
