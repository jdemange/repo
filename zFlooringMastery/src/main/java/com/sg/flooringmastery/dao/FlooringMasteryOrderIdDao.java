/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.OrderId;

/**
 *
 * @author apprentice
 */
public interface FlooringMasteryOrderIdDao {
    public OrderId getId(String currentFile) throws FlooringMasteryPersistenceException;
    public String setId(OrderId newId, String currentFile) throws FlooringMasteryPersistenceException;
    
    public boolean isTraining();

    public void setTraining(boolean training);
}
