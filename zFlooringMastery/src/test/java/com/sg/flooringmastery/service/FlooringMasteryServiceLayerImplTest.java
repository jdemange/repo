/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
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
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryServiceLayerImplTest {

    FlooringMasteryServiceLayer service;
    

    public FlooringMasteryServiceLayerImplTest() {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("service", FlooringMasteryServiceLayer.class);
        
        

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        OrderId id = new OrderId(1);
        id.setIdNumber(1);
        Order orderA = new Order(id);
        orderA.setOrderNumber(id);
        LocalDate date = LocalDate.parse("05/05/2005", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        orderA.setOrderDate(date);
        StateTaxRate tax = new StateTaxRate("Ohio");
        tax.setState("Ohio");
        tax.setTaxRate(new BigDecimal(3.45));
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
        orderA.setMaterialCost(new BigDecimal(100));
        orderA.setCustomerName("Jocelyn DeMange");
        orderA.setTotalCost(new BigDecimal (500));
        service.addAndSaveOrder(orderA);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of retrieveOrdersByDate method, of class
     * FlooringMasteryServiceLayerImpl.
     */
    @Test
    public void testRetrieveOrdersByDate() throws Exception {
       OrderId id = new OrderId(1);
        id.setIdNumber(1);
        Order orderA = new Order(id);
        orderA.setOrderNumber(id);
        LocalDate date = LocalDate.parse("05/05/2005", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        orderA.setOrderDate(date);
        StateTaxRate tax = new StateTaxRate("Ohio");
        tax.setState("Ohio");
        tax.setTaxRate(new BigDecimal(3.45));
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
        orderA.setMaterialCost(new BigDecimal(100));
        orderA.setCustomerName("Jocelyn DeMange");
        orderA.setTotalCost(new BigDecimal (500));

        LocalDate testDate = LocalDate.parse("05/05/2005", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        List<Order> ordersList = service.retrieveOrdersByDate(testDate);
        Order testOrder = ordersList.get(0);
        assertTrue(checkIfEqual(orderA, testOrder));

    }

    /**
     * Test of getNewIdAndCreateOrder method, of class
     * FlooringMasteryServiceLayerImpl.
     */
    @Test
    public void testGetNewIdAndCreateOrder() throws Exception {
        Order testOrder = service.getNewIdAndCreateOrder();
        assertFalse(testOrder.getOrderNumber().getIdNumber() == 0);
    }

    /**
     * Test of checkIfStateIsValid method, of class
     * FlooringMasteryServiceLayerImpl.
     */
    @Test (expected=StateDoesNotExistException.class)
    public void testCheckIfStateIsValid() throws Exception {
        service.checkIfStateIsValid("jd");
    }
   @Test 
    public void testCheckIfStateIsValid2() throws Exception {
        StateTaxRate test = service.checkIfStateIsValid("oh");
        assertTrue(test.getState().equals("OH"));
    }

    /**
     * Test of retrieveProductList method, of class
     * FlooringMasteryServiceLayerImpl.
     */
    @Test
    public void testRetrieveProductList() throws Exception {
        List<Product> testList = service.retrieveProductList();
        assertTrue(testList.size() != 0);
    }

    /**
     * Test of completeOrderCaluclations method, of class
     * FlooringMasteryServiceLayerImpl.
     */
    @Test
    public void testCompleteOrderCaluclations() throws Exception {
        OrderId id = new OrderId(6);
        id.setIdNumber(6);
        Order testOrder = new Order(id);
        testOrder.setOrderNumber(id);
        testOrder.setCustomerName("Jim");
        Product testProduct = new Product("Bamboo");
        testProduct.setProductType("Bamboo");
        testProduct.setMaterialCostPerSQFT(new BigDecimal(100.33));
        testProduct.setLaborCostPerSQFT(new BigDecimal(30.50));
        testOrder.setProductInfo(testProduct);
        testOrder.setArea(new BigDecimal(500));
        StateTaxRate testRate = new StateTaxRate("VA");
        testRate.setState("VA");
        testRate.setTaxRate(new BigDecimal(9.5));
        testOrder.setStateAndTaxRate(testRate);
        
        testOrder = service.completeOrderCaluclations(testOrder);
        
        BigDecimal laborCost = new BigDecimal(30.50).multiply(new BigDecimal(500));
       
        assertTrue(testOrder.getLaborCost().compareTo(laborCost)==0);
        
        
    }
    
        @Test
    public void MATERIALtestCompleteOrderCaluclations() throws Exception {
        OrderId id = new OrderId(6);
        id.setIdNumber(6);
        Order testOrder = new Order(id);
        testOrder.setOrderNumber(id);
        testOrder.setCustomerName("Jim");
        Product testProduct = new Product("Bamboo");
        testProduct.setProductType("Bamboo");
        testProduct.setMaterialCostPerSQFT(new BigDecimal(100.33));
        testProduct.setLaborCostPerSQFT(new BigDecimal(30.50));
        testOrder.setProductInfo(testProduct);
        testOrder.setArea(new BigDecimal(500));
        StateTaxRate testRate = new StateTaxRate("VA");
        testRate.setState("VA");
        testRate.setTaxRate(new BigDecimal(9.5));
        testOrder.setStateAndTaxRate(testRate);
        
        testOrder = service.completeOrderCaluclations(testOrder);
        
        BigDecimal materialCost = new BigDecimal(100.33).multiply(new BigDecimal(500)).setScale(2, RoundingMode.HALF_UP);
        assertTrue(testOrder.getMaterialCost().toString().equals(materialCost.toString()));
        
    }
            @Test
    public void TaxAndTotaltestCompleteOrderCaluclations() throws Exception {
        OrderId id = new OrderId(6);
        id.setIdNumber(6);
        Order testOrder = new Order(id);
        testOrder.setOrderNumber(id);
        testOrder.setCustomerName("Jim");
        Product testProduct = new Product("Bamboo");
        testProduct.setProductType("Bamboo");
        testProduct.setMaterialCostPerSQFT(new BigDecimal(100.33));
        testProduct.setLaborCostPerSQFT(new BigDecimal(30.50));
        testOrder.setProductInfo(testProduct);
        testOrder.setArea(new BigDecimal(500));
        StateTaxRate testRate = new StateTaxRate("VA");
        testRate.setState("VA");
        testRate.setTaxRate(new BigDecimal(9.33));
        testOrder.setStateAndTaxRate(testRate);
        
        testOrder = service.completeOrderCaluclations(testOrder);
        
        BigDecimal laborCost = new BigDecimal(30.50).multiply(new BigDecimal(500)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal materialCost = new BigDecimal(100.33).multiply(new BigDecimal(500)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal subTotal = laborCost.add(materialCost);
        BigDecimal totalTax = subTotal.multiply(new BigDecimal(9.33).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalCost = subTotal.add(totalTax);
        assertTrue(testOrder.getTaxCost().toString().equals(totalTax.toString()));
        assertTrue(testOrder.getTotalCost().toString().equals(totalCost.toString()));
    }

    /**
     * Test of addAndSaveOrder method, of class FlooringMasteryServiceLayerImpl.
     */
    @Test
    public void testAddAndSaveOrder() throws Exception {
        OrderId id = new OrderId(1);
        id.setIdNumber(1);
        Order testOrder = new Order(id);
        testOrder.setOrderNumber(id);
        LocalDate date = LocalDate.parse("05/05/2005", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        testOrder.setOrderDate(date);
        StateTaxRate tax = new StateTaxRate("Ohio");
        tax.setState("Ohio");
        tax.setTaxRate(new BigDecimal(3.45));
        testOrder.setStateAndTaxRate(tax);
        Product prodA = new Product("Wood");
        prodA.setProductType("Wood");
        prodA.setMaterialCostPerSQFT(new BigDecimal(3.50));
        prodA.setLaborCostPerSQFT(new BigDecimal(4.67));
        testOrder.setProductInfo(prodA);
        testOrder.setArea(new BigDecimal(122.33));
        testOrder.setLaborCost(new BigDecimal(2.33));
        testOrder.setTaxCost(new BigDecimal(12.23));
        testOrder.setLaborCost(new BigDecimal(12233));
        testOrder.setMaterialCost(new BigDecimal(100));
        testOrder.setCustomerName("Jocelyn DeMange");
        testOrder.setTotalCost(new BigDecimal (500));
        
        service.addAndSaveOrder(testOrder);
    }

    /**
     * Test of seeIfDateChangedAndUpdate method, of class
     * FlooringMasteryServiceLayerImpl.
     */
    @Test
    public void testSeeIfDateChangedAndUpdate() throws Exception {
        OrderId id = new OrderId(1);
        id.setIdNumber(1);
        Order testOrder = new Order(id);
        testOrder.setOrderNumber(id);
        LocalDate date = LocalDate.parse("05/05/2005", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        testOrder.setOrderDate(date);
        StateTaxRate tax = new StateTaxRate("Ohio");
        tax.setState("Ohio");
        tax.setTaxRate(new BigDecimal(3.45));
        testOrder.setStateAndTaxRate(tax);
        Product prodA = new Product("Wood");
        prodA.setProductType("Wood");
        prodA.setMaterialCostPerSQFT(new BigDecimal(3.50));
        prodA.setLaborCostPerSQFT(new BigDecimal(4.67));
        testOrder.setProductInfo(prodA);
        testOrder.setArea(new BigDecimal(122.33));
        testOrder.setLaborCost(new BigDecimal(2.33));
        testOrder.setTaxCost(new BigDecimal(12.23));
        testOrder.setLaborCost(new BigDecimal(12233));
        testOrder.setMaterialCost(new BigDecimal(100));
        testOrder.setCustomerName("Jocelyn DeMange");
        testOrder.setTotalCost(new BigDecimal (500));
        
        LocalDate testDate = LocalDate.parse("05/05/2005", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        
        service.seeIfDateChangedAndUpdate(testDate, testOrder);
    }
    
        @Test
    public void testSeeIfDateChangedAndUpdate2() throws Exception {
        OrderId id = new OrderId(1);
        id.setIdNumber(1);
        Order testOrder = new Order(id);
        testOrder.setOrderNumber(id);
        LocalDate date = LocalDate.parse("05/05/2005", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        testOrder.setOrderDate(date);
        StateTaxRate tax = new StateTaxRate("Ohio");
        tax.setState("Ohio");
        tax.setTaxRate(new BigDecimal(3.45));
        testOrder.setStateAndTaxRate(tax);
        Product prodA = new Product("Wood");
        prodA.setProductType("Wood");
        prodA.setMaterialCostPerSQFT(new BigDecimal(3.50));
        prodA.setLaborCostPerSQFT(new BigDecimal(4.67));
        testOrder.setProductInfo(prodA);
        testOrder.setArea(new BigDecimal(122.33));
        testOrder.setLaborCost(new BigDecimal(2.33));
        testOrder.setTaxCost(new BigDecimal(12.23));
        testOrder.setLaborCost(new BigDecimal(12233));
        testOrder.setMaterialCost(new BigDecimal(100));
        testOrder.setCustomerName("Jocelyn DeMange");
        testOrder.setTotalCost(new BigDecimal (500));
        
        LocalDate testDate = LocalDate.parse("05/06/2005", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        //add assumption
        service.seeIfDateChangedAndUpdate(testDate, testOrder);
    }

    /**
     * Test of removeOrderFromSystem method, of class
     * FlooringMasteryServiceLayerImpl.
     */
    @Test 
    public void testRemoveOrderFromSystem() throws Exception {
         LocalDate date = LocalDate.parse("05/05/2005", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
         int orderNumber = 1;
         //add assumption
         service.removeOrderFromSystem(date, orderNumber);
    }

    /**
     * Test of setMode method, of class FlooringMasteryServiceLayerImpl.
     */
    @Test
    public void testSetMode() {
    }

    private boolean checkIfEqual(Order orderA, Order orderB) {
        return (orderA.getArea().compareTo(orderB.getArea()) == 0
                && orderA.getLaborCost().compareTo(orderB.getLaborCost()) == 0
                && orderA.getMaterialCost().compareTo(orderB.getMaterialCost()) == 0
                && orderA.getStateAndTaxRate().getTaxRate().compareTo(orderB.getStateAndTaxRate().getTaxRate()) == 0
                && orderA.getTaxCost().compareTo(orderB.getTaxCost()) == 0
                && orderA.getTotalCost().compareTo(orderB.getTotalCost()) == 0
                && orderA.getProductInfo().getMaterialCostPerSQFT().compareTo(orderA.getProductInfo().getMaterialCostPerSQFT()) == 0
                && orderA.getProductInfo().getLaborCostPerSQFT().compareTo(orderA.getProductInfo().getLaborCostPerSQFT()) == 0
                && orderA.getOrderNumber().getIdNumber() - orderB.getOrderNumber().getIdNumber() == 0
                && orderA.getStateAndTaxRate().getState().equals(orderB.getStateAndTaxRate().getState()) == true
                && orderA.getProductInfo().getProductType().equals(orderB.getProductInfo().getProductType()) == true
                && orderA.getCustomerName().equals(orderB.getCustomerName()) == true);
    }

}
