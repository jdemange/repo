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
public class FlooringMasteryOrderIdDaoStubImpl implements FlooringMasteryOrderIdDao{
   
    
    
    @Override
    public OrderId getId(String currentFile) throws FlooringMasteryPersistenceException {
        OrderId id = new OrderId(1);
        return id;
    }

    @Override
    public String setId(OrderId newId, String currentFile) throws FlooringMasteryPersistenceException {
        
        OrderId id = new OrderId(1);
        return String.valueOf(newId.getIdNumber());
    }

    @Override
    public boolean isTraining() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTraining(boolean training) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
