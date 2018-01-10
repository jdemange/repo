/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.StateTaxRate;
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
public class FlooringMasteryStateTaxRateDaoFileImpl implements FlooringMasteryStateTaxRateDao{
    
    private Map<String, StateTaxRate> taxRates = new HashMap<>();
    public static final String TAX_RATES = "taxes.txt";
    public static final String DELIMITER = ",";

    @Override
    public List<StateTaxRate> retrieveTaxRates(String currentFile) throws FlooringMasteryPersistenceException {
        
        loadTaxRates(currentFile);
        return new ArrayList<StateTaxRate>(taxRates.values());
        
    }
        public StateTaxRate checkIfStateIsValid(String state) throws FlooringMasteryPersistenceException, StateDoesNotExistException{
        loadTaxRates(TAX_RATES);
        try{
            state = taxRates.get(state).getState();
        } catch (NullPointerException e){
            throw new StateDoesNotExistException("We are unable to conduct"
               + " business in the state that you entered. Please choose another "
               + "state.", e);
                    }
        return taxRates.get(state);
    }
    
    
        private void loadTaxRates(String currentFile) throws FlooringMasteryPersistenceException {

        Scanner scanner = null;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(currentFile)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException("We are unable to access our tax rate"
                    + " inforamtion. Please contact the help desk before moving forward.", e);

        }

        String currentLine;
        String[] currentTokens;
        
        scanner.nextLine();
          

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            StateTaxRate currentItem = new StateTaxRate(currentTokens[0]);
            currentItem.setState(currentTokens[0]);
            

            String stringTaxRate = currentTokens[1];
            BigDecimal bigTaxRate = new BigDecimal(stringTaxRate); 
            currentItem.setTaxRate(bigTaxRate);
            

            taxRates.put(currentItem.getState(), currentItem);
        }

    }


    
}
