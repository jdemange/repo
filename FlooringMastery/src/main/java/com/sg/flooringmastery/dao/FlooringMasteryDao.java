/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface FlooringMasteryDao {

    
    public List<Order> getOrdersByDate(LocalDate requestedDate) throws FlooringMasteryPersistenceException;
    public boolean checkIfFilesExist(String fileName);
    public void addToMap(Order order)throws FlooringMasteryPersistenceException;
    public void writeToFile()throws FlooringMasteryPersistenceException;
    public void removeOrder(Order orderToEdit) throws FlooringMasteryPersistenceException;
    public void removeFromMap(int orderNumber) throws FlooringMasteryPersistenceException;
    public boolean checkIfMapisEmpty (String fileLocation) throws FlooringMasteryPersistenceException;
    public void clearMap ();
    public boolean isTraining();
    public void setTraining(boolean training);
}
