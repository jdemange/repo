/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.StateTaxRate;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface FlooringMasteryStateTaxRateDao {
    public List<StateTaxRate> retrieveTaxRates(String currentFile) throws FlooringMasteryPersistenceException;
    public StateTaxRate checkIfStateIsValid(String state) throws FlooringMasteryPersistenceException, StateDoesNotExistException;
}

