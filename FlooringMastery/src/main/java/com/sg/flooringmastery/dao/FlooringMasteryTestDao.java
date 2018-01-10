/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.OrderId;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.StateTaxRate;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
public class FlooringMasteryTestDao implements FlooringMasteryDao {

    private Map<Integer, Order> orders = new HashMap<>();
    private boolean training;
    public static final String FILE_NAME_START = "Orders/Orders_";
    public static final String DELIMITER = ",";
    public static final String[] FIRST_LINE = {"OrderNumber", "CustomerName", "State",
             "TaxRate","ProductType","Area","CostPerSquareFoot","LaborCostPerSquareFoot"
             ,"MaterialCost","LaborCost","Tax","Total"};

    public FlooringMasteryTestDao() {
        training = false;
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate requestedDate) throws FlooringMasteryPersistenceException {
        loadOrders(requestedDate);
        return new ArrayList<Order>(orders.values());
    }

    @Override
    public boolean checkIfFilesExist(String fileName) {
        File check = new File(fileName);
        return check.exists();
    }

    @Override
    public void addToMap(Order order) throws FlooringMasteryPersistenceException {

        orders.put(order.getOrderNumber().getIdNumber(), order);

    }

    @Override
    public void removeFromMap(int orderNumber) throws FlooringMasteryPersistenceException {
        orders.remove(orderNumber);
    }

    @Override
    public void writeToFile() throws FlooringMasteryPersistenceException {
        writeOrders();
    }

    @Override
    public boolean checkIfMapisEmpty(String fileLocation) throws FlooringMasteryPersistenceException {
        PrintWriter out;
        if (orders.size() == 0 && isTraining() == false) {
            File emptyFile = new File(fileLocation);
            emptyFile.delete();
            return true;
        }
        return false;
    }

    @Override
    public void clearMap() {
        orders.clear();
    }

    private void loadOrders(LocalDate requestedDate) throws FlooringMasteryPersistenceException {

        orders.clear();
        String stringRequestedDate = requestedDate.format(DateTimeFormatter.ofPattern("MMddYYYY"));
        String fileLocation = FILE_NAME_START + stringRequestedDate + ".csv";

        CSVReader reader = null;

        try {
            reader = new CSVReader(new FileReader(fileLocation));

            String[] currentTokens;

            reader.readNext();

            while ((currentTokens = reader.readNext()) != null) {

                String stringOrderId = currentTokens[0];
                int intOrderId = Integer.parseInt(stringOrderId);
                OrderId currentId = new OrderId(intOrderId);
                Order currentOrder = new Order(currentId);

                currentOrder.setOrderNumber(currentId);

                currentOrder.setCustomerName(currentTokens[1]);

                String currentState = currentTokens[2];
                String stringTaxRate = currentTokens[3];
                BigDecimal taxRate = new BigDecimal(stringTaxRate);
                StateTaxRate orderTaxRate = new StateTaxRate(currentState);
                orderTaxRate.setState(currentState);
                orderTaxRate.setTaxRate(taxRate);
                currentOrder.setStateAndTaxRate(orderTaxRate);

                String productType = currentTokens[4];
                String stringMaterialCost = currentTokens[6];
                BigDecimal materialCost = new BigDecimal(stringMaterialCost);
                String stringLaborCost = currentTokens[7];
                BigDecimal laborCost = new BigDecimal(stringLaborCost);
                Product currentProduct = new Product(productType);
                currentProduct.setProductType(productType);
                currentProduct.setMaterialCostPerSQFT(materialCost);
                currentProduct.setLaborCostPerSQFT(laborCost);
                currentOrder.setProductInfo(currentProduct);

                BigDecimal area = new BigDecimal(currentTokens[5]);
                currentOrder.setArea(area);

                BigDecimal totalMaterialCost = new BigDecimal(currentTokens[8]);
                currentOrder.setMaterialCost(totalMaterialCost);

                BigDecimal totalLaborCost = new BigDecimal(currentTokens[9]);
                currentOrder.setLaborCost(totalLaborCost);

                BigDecimal totalTax = new BigDecimal(currentTokens[10]);
                currentOrder.setTaxCost(totalTax);

                BigDecimal totalCost = new BigDecimal(currentTokens[11]);
                currentOrder.setTotalCost(totalCost);

                currentOrder.setOrderDate(requestedDate);

                orders.put(currentOrder.getOrderNumber().getIdNumber(), currentOrder);

            }
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("Could not load orders. "
                    + "Please seek assistance before proceeding", e);
        }
    }

    private void writeOrders() throws FlooringMasteryPersistenceException {

        if (isTraining()) {
            return;
        }
        
//        CSVWriter writer = new CSVWriter(new FileWriter("yourfile.csv"), '\t');
//         // feed in your array (or convert your data to an array)
//        String[] entrieus = "first#second#third".split("#");
//        writer.writeNext(entrieus);
//	 writer.close();

        List<Order> orderList = new ArrayList<Order>(orders.values());

        Order tempOrder = orderList.get(0);
        LocalDate fileDate = tempOrder.getOrderDate();
        String stringFileDate = fileDate.format(DateTimeFormatter.ofPattern("MMddYYYY"));
        String fileLocation = FILE_NAME_START + stringFileDate + ".csv";

        try {
            CSVWriter out = new CSVWriter(new FileWriter(fileLocation),',',CSVWriter.NO_QUOTE_CHARACTER);
     

        out.writeNext(FIRST_LINE);

        for (Order currentOrder : orderList) {

            OrderId currentId = currentOrder.getOrderNumber();
            int Id = currentId.getIdNumber();

            StateTaxRate currentStateTax = currentOrder.getStateAndTaxRate();
            String currentState = currentStateTax.getState();
            BigDecimal currentTaxRate = currentStateTax.getTaxRate();

            Product currentProduct = currentOrder.getProductInfo();
            String currentType = currentProduct.getProductType();
            BigDecimal currentMaterialCost = currentProduct.getMaterialCostPerSQFT();
            BigDecimal currentLaborCost = currentProduct.getLaborCostPerSQFT();

            String[] entries =
            {Integer.toString(Id),
                     currentOrder.getCustomerName(),
                     currentState,
                     currentTaxRate.toString(),
                     currentType,
                     currentOrder.getArea().toString(),
                     currentMaterialCost.toString(),
                     currentLaborCost.toString(),
                     currentOrder.getMaterialCost().toString(),
                     currentOrder.getLaborCost().toString(),
                     currentOrder.getTaxCost().toString(),
                     currentOrder.getTotalCost().toString()};

            out.writeNext(entries);
        }
        out.close();
           } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("Could not save student data.", e);
        }
    }

    public void removeOrder(Order orderToEdit) throws FlooringMasteryPersistenceException {
        removeFromMap(orderToEdit.getOrderNumber().getIdNumber());
        writeToFile();
    }

    public boolean isTraining() {
        return training;
    }

    public void setTraining(boolean training) {
        this.training = training;
    }

    public Map<Integer, Order> getOrders() {
        return orders;
    }

    public void setOrders(Map<Integer, Order> orders) {
        this.orders = orders;
    }
}
