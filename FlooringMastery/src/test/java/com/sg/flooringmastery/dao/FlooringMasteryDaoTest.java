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
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryDaoTest {
    FlooringMasteryDao dao = new FlooringMasteryProductionDao();
    public FlooringMasteryDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws FlooringMasteryPersistenceException {
         OrderId id = new OrderId(1);
        id.setIdNumber(1);
        Order orderA = new Order(id);
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
        
        OrderId id2 = new OrderId(2);
        id2.setIdNumber(2);
        Order orderC = new Order(id2);
        orderC.setOrderNumber(id2);
        LocalDate trash = LocalDate.parse("05/05/2005", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        orderC.setOrderDate(trash);
        StateTaxRate tax2 = new StateTaxRate("Indiana");
        tax2.setState("Ohio");
        tax2.setTaxRate(new BigDecimal (0.45));
        orderC.setStateAndTaxRate(tax2);
        Product prodC = new Product("Laminate");
        prodC.setProductType("Laminate");
        prodC.setMaterialCostPerSQFT(new BigDecimal(3.59));
        prodC.setLaborCostPerSQFT(new BigDecimal(7.67));
        orderC.setProductInfo(prodC);
        orderC.setArea(new BigDecimal(124.33));
        orderC.setLaborCost(new BigDecimal(4.33));
        orderC.setTaxCost(new BigDecimal(12.83));
        orderC.setLaborCost(new BigDecimal(8233));
        
        dao.addToMap(orderC);
        dao.addToMap(orderA);
    }
    
    @After
    public void tearDown() {
       
    }

    /**
     * Test of getOrdersByDate method, of class FlooringMasteryDao.
     */
    @Test (expected=FlooringMasteryPersistenceException.class)
    public void testGetOrdersByDate() throws Exception {
        LocalDate testDate = LocalDate.parse("05/05/9000", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        List<Order> orderList = dao.getOrdersByDate(testDate);

    }

    /**
     * Test of checkIfFilesExist method, of class FlooringMasteryDao.
     */
    @Test
    public void testCheckIfFilesExist() {
        assertFalse(dao.checkIfFilesExist("test"));
        assertFalse(dao.checkIfFilesExist("09093000"));
    }

    /**
     * Test of addToMap method, of class FlooringMasteryDao.
     */
    @Test
    public void testAddToMap() {
        //see testWriteToFileAndRemoveOrder
    }

    /**
     * Test of writeToFile method, of class FlooringMasteryDao.
     */
    @Test
    public void testWriteToFileAndRemoveOrder() throws Exception {
        OrderId id2 = new OrderId(2);
        id2.setIdNumber(2);
        Order orderC = new Order(id2);
        orderC.setOrderNumber(id2);
        LocalDate trash = LocalDate.parse("05/05/1500", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        orderC.setOrderDate(trash);
        StateTaxRate tax2 = new StateTaxRate("Indiana");
        tax2.setState("Ohio");
        tax2.setTaxRate(new BigDecimal (0.45));
        orderC.setStateAndTaxRate(tax2);
        Product prodC = new Product("Laminate");
        prodC.setProductType("Laminate");
        prodC.setMaterialCostPerSQFT(new BigDecimal(3.59));
        prodC.setLaborCostPerSQFT(new BigDecimal(7.67));
        orderC.setProductInfo(prodC);
        orderC.setArea(new BigDecimal(124.33));
        orderC.setLaborCost(new BigDecimal(4.33));
        orderC.setTaxCost(new BigDecimal(12.83));
        orderC.setLaborCost(new BigDecimal(8233));
        orderC.setCustomerName("Jim");
        orderC.setMaterialCost(new BigDecimal(400));
        orderC.setLaborCost(new BigDecimal(100));
        orderC.setTotalCost(new BigDecimal(8000));
        
        OrderId id3 = new OrderId(1);
        id2.setIdNumber(2);
        Order order3 = new Order(id3);
        order3.setOrderNumber(id3);
        LocalDate trash3 = LocalDate.parse("05/05/1500", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        order3.setOrderDate(trash3);
        StateTaxRate tax3 = new StateTaxRate("Indiana");
        tax3.setState("Ohio");
        tax3.setTaxRate(new BigDecimal (0.45));
        order3.setStateAndTaxRate(tax3);
        Product prod3 = new Product("Laminate");
        prod3.setProductType("Laminate");
        prod3.setMaterialCostPerSQFT(new BigDecimal(3.59));
        prod3.setLaborCostPerSQFT(new BigDecimal(7.67));
        order3.setProductInfo(prod3);
        order3.setArea(new BigDecimal(124.33));
        order3.setLaborCost(new BigDecimal(4.33));
        order3.setTaxCost(new BigDecimal(12.83));
        order3.setLaborCost(new BigDecimal(8233));
        order3.setCustomerName("Jim");
        order3.setMaterialCost(new BigDecimal(400));
        order3.setLaborCost(new BigDecimal(100));
        order3.setTotalCost(new BigDecimal(8000));
        
        dao.clearMap();
        dao.addToMap(orderC);
        dao.addToMap(order3);
        dao.writeToFile();
        dao.removeOrder(orderC);
        assertTrue(dao.checkIfFilesExist("Orders/Orders_05051500.txt"));
        dao.removeFromMap(order3.getOrderNumber().getIdNumber());
        dao.checkIfMapisEmpty("Orders/Orders_05051500.txt");
        assertFalse(dao.checkIfFilesExist("Orders/Orders_05051500"));
       
    }

    /**
     * Test of removeOrder method, of class FlooringMasteryDao.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        //see testWriteToFileAndRemoveOrder
    }

    /**
     * Test of removeFromMap method, of class FlooringMasteryDao.
     */
    @Test
    public void testRemoveFromMap() {
        //see testWriteToFileAndRemoveOrder
    }

    /**
     * Test of checkIfMapisEmpty method, of class FlooringMasteryDao.
     */
    @Test
    public void testCheckIfMapisEmpty() throws Exception {
        //see testWriteToFileAndRemoveOrder
    }

    /**
     * Test of clearMap method, of class FlooringMasteryDao.
     */
    @Test(expected=IndexOutOfBoundsException.class)
    public void testClearMap() throws Exception{
        dao.clearMap();
        dao.writeToFile();
        
    }

   
}
