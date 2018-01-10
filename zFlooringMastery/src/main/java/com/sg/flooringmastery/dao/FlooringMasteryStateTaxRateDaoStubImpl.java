/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.StateTaxRate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryStateTaxRateDaoStubImpl implements FlooringMasteryStateTaxRateDao {

    String onlyFileName = "05052005";
    List<StateTaxRate> ratesList = new ArrayList<>();


    @Override
    public List<StateTaxRate> retrieveTaxRates(String currentFile) throws FlooringMasteryPersistenceException {
       StateTaxRate tax = new StateTaxRate("OH");
        tax.setState("OH");
        tax.setTaxRate(new BigDecimal (3.45));
        
        ratesList.add(tax);
        
        
        if (onlyFileName.equals(currentFile)){
            return ratesList;
        } else {
           throw new FlooringMasteryPersistenceException ("I can't get you those tax rates");
        }
            
    }

    @Override
    public StateTaxRate checkIfStateIsValid(String state) throws FlooringMasteryPersistenceException, StateDoesNotExistException {
       StateTaxRate tax = new StateTaxRate("OH");
        tax.setState("OH");
        tax.setTaxRate(new BigDecimal (3.45));
        
        ratesList.add(tax);
        
        if (state.equals(tax.getState())){
            return tax;
        } else {
            throw new StateDoesNotExistException ("That state does not exist");
        }
    }

}
