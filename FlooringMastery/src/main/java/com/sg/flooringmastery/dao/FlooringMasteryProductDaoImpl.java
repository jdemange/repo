/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryProductDaoImpl implements FlooringMasteryProductDao {
    private Map<String, Product> productTypes = new HashMap<>();
    public static final String PRODUCT_LIST = "products.txt";
    public static final String DELIMITER = ",";
    
        
    @Override
        public List<Product> retrieveProductList(String currentFile) throws FlooringMasteryPersistenceException{
            loadProducts(currentFile);
            return new ArrayList<Product>(productTypes.values());
        }
    
    
    
        private void loadProducts(String currentFile) throws FlooringMasteryPersistenceException {

        Scanner scanner = null;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(currentFile)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException("We are unable to access our product inventory"
                    + " inforamtion. Please contact the help desk before moving forward.", e);

        }

        String currentLine;
        String[] currentTokens;
        
        scanner.nextLine();
          

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            Product currentItem = new Product(currentTokens[0]);
            currentItem.setProductType(currentTokens[0]);
            

            String stringMaterialPrice = currentTokens[1];
            BigDecimal bigMaterialPrice = new BigDecimal(stringMaterialPrice); 
            currentItem.setMaterialCostPerSQFT(bigMaterialPrice);
            
            String stringLaborPrice = currentTokens[2];
            BigDecimal bigLaborPrice = new BigDecimal(stringLaborPrice); 
            currentItem.setLaborCostPerSQFT(bigLaborPrice);

            productTypes.put(currentItem.getProductType(), currentItem);
        }

    }
}
