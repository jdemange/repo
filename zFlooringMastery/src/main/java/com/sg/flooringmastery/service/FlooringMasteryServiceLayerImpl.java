/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryDao;
import com.sg.flooringmastery.dao.FlooringMasteryOrderIdDao;
import com.sg.flooringmastery.dao.FlooringMasteryOrderIdDaoFileImpl;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dao.FlooringMasteryProductDao;
import com.sg.flooringmastery.dao.FlooringMasteryProductDaoImpl;
import com.sg.flooringmastery.dao.FlooringMasteryProductionDao;
import com.sg.flooringmastery.dao.FlooringMasteryStateTaxRateDao;
import com.sg.flooringmastery.dao.FlooringMasteryStateTaxRateDaoFileImpl;
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
public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {

    FlooringMasteryProductDao productDao;
    FlooringMasteryDao productionDao;
    FlooringMasteryStateTaxRateDao taxDao;
    FlooringMasteryOrderIdDao idDao;
    public static final String FILE_EXTENSION = ".txt";

    public FlooringMasteryServiceLayerImpl(FlooringMasteryProductDao productDao,
            FlooringMasteryDao productionDao, FlooringMasteryStateTaxRateDao taxDao,
            FlooringMasteryOrderIdDao idDao) {

        this.productDao = productDao;
        this.productionDao = productionDao;
        this.taxDao = taxDao;
        this.idDao = idDao;

    }

    @Override
    public List<Order> retrieveOrdersByDate(LocalDate requestedDate) throws FlooringMasteryPersistenceException {
        return productionDao.getOrdersByDate(requestedDate);
    }

    @Override
    public Order getNewIdAndCreateOrder() throws FlooringMasteryPersistenceException {
        OrderId latestId = idDao.getId(FlooringMasteryOrderIdDaoFileImpl.LATEST_ID);
        int updateId = latestId.getIdNumber() + 1;
        latestId.setIdNumber(updateId);
        Order newOrder = new Order(latestId);
        newOrder.setOrderNumber(latestId);
        idDao.setId(latestId, FlooringMasteryOrderIdDaoFileImpl.LATEST_ID);
        return newOrder;

    }

    @Override
    public StateTaxRate checkIfStateIsValid(String state) throws FlooringMasteryPersistenceException, StateDoesNotExistException {
        return taxDao.checkIfStateIsValid(state.toUpperCase());
    }

    @Override
    public List<Product> retrieveProductList() throws FlooringMasteryPersistenceException {
        return productDao.retrieveProductList(FlooringMasteryProductDaoImpl.PRODUCT_LIST);
    }

    @Override
    public Order completeOrderCaluclations(Order order) throws ImproperOrderInformationException {

        BigDecimal labor = multiplyBigDecimalValues(order.getProductInfo().getLaborCostPerSQFT(), order.getArea()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal material = multiplyBigDecimalValues(order.getProductInfo().getMaterialCostPerSQFT(), order.getArea().setScale(2, RoundingMode.HALF_UP));
        BigDecimal tax = order.getStateAndTaxRate().getTaxRate().divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
        tax = multiplyBigDecimalValues(tax, (labor.add(material))).setScale(2, RoundingMode.HALF_UP);
        BigDecimal total = labor.add(material).add(tax).setScale(2, RoundingMode.HALF_UP);

        order.setLaborCost(labor);
        order.setMaterialCost(material);
        order.setTaxCost(tax);
        order.setTotalCost(total);
        validateOrderHasAllItems(order);
        return order;
    }

    @Override
    public void addAndSaveOrder(Order newOrder) throws FlooringMasteryPersistenceException, ImproperOrderInformationException {
        String fileName = changeDateToFileName(newOrder);
        validateOrderHasAllItems(newOrder);

        boolean exsists = productionDao.checkIfFilesExist(fileName);
        if (exsists) {
            productionDao.getOrdersByDate(newOrder.getOrderDate());
        } else {
            productionDao.clearMap();
        }
        productionDao.addToMap(newOrder);
        productionDao.writeToFile();

    }

    @Override
    public void seeIfDateChangedAndUpdate(LocalDate originalDate, Order updatedOrder) throws FlooringMasteryPersistenceException {

        int sameDate = originalDate.compareTo(updatedOrder.getOrderDate());

        if (sameDate != 0) {
            removeOrderFromSystem(originalDate, updatedOrder.getOrderNumber().getIdNumber());
        } else {
            String fileName = changeDateToFileName(updatedOrder);
            boolean exist = productionDao.checkIfFilesExist(fileName);
            if (exist) {
                productionDao.getOrdersByDate(updatedOrder.getOrderDate());
            }

        }
    }

    @Override
    public void removeOrderFromSystem(LocalDate originalDate, int orderNumber) throws FlooringMasteryPersistenceException {
        productionDao.getOrdersByDate(originalDate);
        productionDao.removeFromMap(orderNumber);
        boolean empty = productionDao.checkIfMapisEmpty(changeDateToFileName(originalDate));
        if (empty == false){
        productionDao.writeToFile();
        }
    }

    @Override
    public void setMode(boolean trainingMode) {
        productionDao.setTraining(trainingMode);
        idDao.setTraining(trainingMode);
    }

    private void validateOrderHasAllItems(Order order) throws ImproperOrderInformationException {
        if (order.getOrderNumber().getIdNumber() == 0
                || order.getCustomerName() == null
                || order.getCustomerName().trim().length() == 0
                || order.getStateAndTaxRate().getState() == null
                || order.getStateAndTaxRate().getState().trim().length() == 0
                || order.getStateAndTaxRate().getTaxRate() == null
                || order.getProductInfo().getProductType() == null
                || order.getProductInfo().getProductType().trim().length() == 0
                || order.getProductInfo().getMaterialCostPerSQFT() == null
                || order.getProductInfo().getLaborCostPerSQFT() == null
                || order.getArea() == null
                || order.getMaterialCost() == null
                || order.getLaborCost() == null
                || order.getTaxCost() == null
                || order.getTotalCost() == null) {

            throw new ImproperOrderInformationException("The order does not have"
                    + "all of the proper information");
        }
    }

    private BigDecimal multiplyBigDecimalValues(BigDecimal cost, BigDecimal area) {
        return cost.multiply(area).setScale(2, RoundingMode.HALF_UP);
    }

    private String changeDateToFileName(Order newOrder) {
        LocalDate orderDate = newOrder.getOrderDate();
        String stringFileDate = orderDate.format(DateTimeFormatter.ofPattern("MMddYYYY"));
        return "Orders/Orders_" + stringFileDate + FILE_EXTENSION;
    }

    private String changeDateToFileName(LocalDate orderDate) {
        String stringFileDate = orderDate.format(DateTimeFormatter.ofPattern("MMddYYYY"));
        return "Orders/Orders_" + stringFileDate + FILE_EXTENSION;
    }
}
