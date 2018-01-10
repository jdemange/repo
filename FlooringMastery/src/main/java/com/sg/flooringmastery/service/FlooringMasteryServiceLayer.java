/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryOrderIdDaoFileImpl;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dao.FlooringMasteryProductDaoImpl;
import com.sg.flooringmastery.dao.StateDoesNotExistException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.OrderId;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.StateTaxRate;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface FlooringMasteryServiceLayer {
   public List<Order> retrieveOrdersByDate(LocalDate requestedDate) throws FlooringMasteryPersistenceException;
   

    public Order getNewIdAndCreateOrder() throws FlooringMasteryPersistenceException;

    public StateTaxRate checkIfStateIsValid(String state) throws FlooringMasteryPersistenceException, StateDoesNotExistException;

    public List<Product> retrieveProductList() throws FlooringMasteryPersistenceException;

    public Order completeOrderCaluclations(Order order) throws ImproperOrderInformationException;

    public void addAndSaveOrder(Order newOrder) throws FlooringMasteryPersistenceException, ImproperOrderInformationException;

    public void seeIfDateChangedAndUpdate(LocalDate originalDate, Order updatedOrder) throws FlooringMasteryPersistenceException;
    
    public void removeOrderFromSystem (LocalDate originalDate, int orderNumber) throws FlooringMasteryPersistenceException; 

    public void setMode(boolean trainingMode);
    
}
