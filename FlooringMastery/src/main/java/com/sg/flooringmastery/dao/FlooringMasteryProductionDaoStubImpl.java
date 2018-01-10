/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.OrderId;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.StateTaxRate;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryProductionDaoStubImpl implements FlooringMasteryDao {
Order orderA;
Order orderC;
String onlyFileName;
    List<Order> orderList = new ArrayList<>();
    Map<Integer,Order> testMap = new HashMap<>(); 
    
    public  FlooringMasteryProductionDaoStubImpl(){
        onlyFileName = "05052005";
        
    OrderId id = new OrderId(1);
        id.setIdNumber(1);
        orderA = new Order(id);
        orderA.setOrderNumber(id);
        LocalDate date = LocalDate.parse("05/05/2005", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        orderA.setOrderDate(date);
        StateTaxRate tax = new StateTaxRate("Ohio");
        tax.setState("Ohio");
        tax.setTaxRate(new BigDecimal (3.45));
        orderA.setStateAndTaxRate(tax);
        Product prodA = new Product("Wood");
        prodA.setProductType("Wood");
        prodA.setMaterialCostPerSQFT(new BigDecimal(3.50));
        prodA.setLaborCostPerSQFT(new BigDecimal(4.67));
        orderA.setProductInfo(prodA);
        orderA.setArea(new BigDecimal(122.33));
        orderA.setLaborCost(new BigDecimal(2.33));
        orderA.setTaxCost(new BigDecimal(12.23));
        orderA.setLaborCost(new BigDecimal(12233));
        
        orderList.add(orderA);
        
}
    @Override
    public List<Order> getOrdersByDate(LocalDate requestedDate) throws FlooringMasteryPersistenceException {
        return new ArrayList<Order>(testMap.values());
    }

    @Override
    public boolean checkIfFilesExist(String fileName)  {
        return fileName.equals(onlyFileName);
    }

    @Override
    public void addToMap(Order order) throws FlooringMasteryPersistenceException{
       
       testMap.put(order.getOrderNumber().getIdNumber(), order);
        
    }

    @Override
    public void writeToFile() throws FlooringMasteryPersistenceException {
        
        
    }

    @Override
    public void removeOrder(Order orderToEdit) throws FlooringMasteryPersistenceException {
        testMap.remove(orderToEdit.getOrderNumber().getIdNumber());
        
       
    }

    @Override
    public void removeFromMap(int orderNumber) throws FlooringMasteryPersistenceException {
        testMap.remove(orderNumber);
       }
    

    @Override
    public boolean checkIfMapisEmpty(String fileLocation) throws FlooringMasteryPersistenceException {
        if(testMap.isEmpty()){
           return true;
        }
        return false;
    }

    @Override
    public void clearMap() {
        orderList.clear();
    }
    
//      private boolean testIfEqual(Order orderA, Order orderB) {
//        return (orderA.getArea().compareTo(orderB.getArea()) == 0
//        && orderA.getLaborCost().compareTo(orderB.getLaborCost()) == 0
//        && orderA.getMaterialCost().compareTo(orderB.getMaterialCost()) == 0
//        && orderA.getStateAndTaxRate().getTaxRate().compareTo(orderB.getStateAndTaxRate().getTaxRate()) == 0
//        && orderA.getTaxCost().compareTo(orderB.getTaxCost()) == 0
//        &&orderA.getTotalCost().compareTo(orderB.getTotalCost()) == 0
//        && orderA.getProductInfo().getMaterialCostPerSQFT().compareTo(orderA.getProductInfo().getMaterialCostPerSQFT()) == 0
//        && orderA.getProductInfo().getLaborCostPerSQFT().compareTo(orderA.getProductInfo().getLaborCostPerSQFT()) == 0
//        
//        && orderA.getOrderNumber().getIdNumber() - orderB.getOrderNumber().getIdNumber() == 0
//        
//        && orderA.getStateAndTaxRate().getState().equals(orderB.getStateAndTaxRate().getState()) == true
//        && orderA.getProductInfo().getProductType().equals(orderB.getProductInfo().getProductType()) == true
//        && orderA.getCustomerName().equals(orderB.getCustomerName()) == true);
//      }

    @Override
    public boolean isTraining() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTraining(boolean training) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
