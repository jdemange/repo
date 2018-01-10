/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dao.StateDoesNotExistException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.StateTaxRate;
import com.sg.flooringmastery.service.FlooringMasteryServiceLayer;
import com.sg.flooringmastery.service.FlooringMasteryServiceLayerImpl;
import com.sg.flooringmastery.service.ImproperOrderInformationException;
import com.sg.flooringmastery.ui.FlooringMasteryView;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryController {

    FlooringMasteryView view;
    FlooringMasteryServiceLayer service;

    public FlooringMasteryController(FlooringMasteryView view, FlooringMasteryServiceLayer service)
            throws FlooringMasteryPersistenceException, StateDoesNotExistException {
        this.view = view;
        this.service = service;
    }

    public void run() throws StateDoesNotExistException, ImproperOrderInformationException {
        boolean keepGoing = true;
        int userChoice;
        try {
            while (keepGoing) {

                view.displayMainMenu();
                userChoice = view.getUserIntegerChoiceWithLimits(1, 7);

                switch (userChoice) {
                    case 1:
                        displayOrdersByDate();
                        break;
                    case 2:
                        addNewOrder();
                        break;
                    case 3:
                        editExistingOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        saveCurrentWork();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    case 7:
                        changeMode();
                        break;
                    default:
                        break;
                }

            }
        } catch (FlooringMasteryPersistenceException e) {
            view.displayAPrompt(e.getMessage());
        }

    }

    public void displayOrdersByDate() throws FlooringMasteryPersistenceException {
        try {
            LocalDate requestedDate = view.getDateFromUser();
            List<Order> ordersList = service.retrieveOrdersByDate(requestedDate);
            view.displayRequestedOrders(ordersList);
        } catch (FlooringMasteryPersistenceException e) {
            view.displayAPrompt("I'm sorry no orders exist for the requested date.");
        }
    }

    public void addNewOrder() throws FlooringMasteryPersistenceException, StateDoesNotExistException,
            ImproperOrderInformationException {
        try {
            String prompt = "Input New";

            Order newOrder = service.getNewIdAndCreateOrder();
            view.displayFullOrderDetails(newOrder);

            getInformationAndMakeCalulcations(newOrder, prompt);
            view.displayFullOrderDetails(newOrder);

            boolean save = view.wouldYouLikeToPersist("Would you like to save this informtion?");

            if (save) {
                if (view.isTraining()) {
                    view.displayAPrompt("Unable to save orders in Training Mode");
                } else {
                    service.addAndSaveOrder(newOrder);
                }
            }
        } catch (FlooringMasteryPersistenceException | StateDoesNotExistException
                | ImproperOrderInformationException e) {
            view.displayAPrompt(e.getMessage());
        }
    }

    public void editExistingOrder() throws FlooringMasteryPersistenceException, StateDoesNotExistException, ImproperOrderInformationException {
        boolean keepGoing = true;

        while (keepGoing) {

            try {
                LocalDate requestedDate = view.getDateFromUser();
                List<Order> ordersList = service.retrieveOrdersByDate(requestedDate);
                Order orderToEdit = view.getOrderFromList(ordersList);
                LocalDate originalDate = orderToEdit.getOrderDate();
                Order updatedOrder = getInformationAndMakeCalulcations(orderToEdit, "Update Field: ");
                view.displayFullOrderDetails(updatedOrder);
                keepGoing = false;

                boolean save = view.wouldYouLikeToPersist("Would you like to save this informtion?");

                if (save) {
                    if (view.isTraining()) {
                    view.displayAPrompt("Unable to save changes in Training Mode");
                } else {
                    service.seeIfDateChangedAndUpdate(originalDate, updatedOrder);
                    service.addAndSaveOrder(updatedOrder);
                    }
                }

            } catch (FlooringMasteryPersistenceException | ImproperOrderInformationException
                    | StateDoesNotExistException e) {
                view.displayAPrompt("I'm sorry no orders exist for the requested date.");
                keepGoing = false;
            }
        }

    }

    public Order getInformationAndMakeCalulcations(Order newOrder, String prompt)
            throws FlooringMasteryPersistenceException, ImproperOrderInformationException,
            StateDoesNotExistException {
        newOrder = view.getCustomerName(newOrder, prompt);
        view.displayFullOrderDetails(newOrder);

        view.addEditDate(prompt, newOrder);
        view.displayFullOrderDetails(newOrder);

        boolean isValid = false;
        while (isValid == false) {
            try {
                String state = view.getStateName(newOrder, prompt);
                StateTaxRate taxRate = service.checkIfStateIsValid(state);
                newOrder.setStateAndTaxRate(taxRate);
                isValid = true;
            } catch (StateDoesNotExistException e) {
                view.displayAPrompt(e.getMessage());
            }
        }
        view.displayFullOrderDetails(newOrder);
        List<Product> productList = service.retrieveProductList();

        newOrder = view.getProductInformation(prompt, newOrder, productList);
        view.displayFullOrderDetails(newOrder);

        newOrder = view.addEditArea(prompt, newOrder);
        try {
            return service.completeOrderCaluclations(newOrder);
        } catch (ImproperOrderInformationException e) {
            view.displayAPrompt(e.getMessage());

        }
        return null;
    }

    public void removeOrder() throws FlooringMasteryPersistenceException, StateDoesNotExistException {
        try {
            LocalDate requestedDate = view.getDateFromUser();
            List<Order> ordersList = service.retrieveOrdersByDate(requestedDate);
            Order orderToRemove = view.getOrderFromList(ordersList);
            view.displayFullOrderDetails(orderToRemove);
            boolean delete = view.wouldYouLikeToPersist("Are you sure you would like to "
                    + "perminently delete this record?");

            if (delete) {
                int num = orderToRemove.getOrderNumber().getIdNumber();
                service.removeOrderFromSystem(orderToRemove.getOrderDate(),num);
                view.displayAPrompt("Order " + Integer.toString(
                        orderToRemove.getOrderNumber().getIdNumber())
                        + " has been deleted.");
            } else {
                view.displayAPrompt("Order " + Integer.toString(
                        orderToRemove.getOrderNumber().getIdNumber())
                        + " will not be deleted.");
            }

        } catch (FlooringMasteryPersistenceException e) {
            view.displayAPrompt("I'm sorry no orders exist for the requested date.");
        }
    }

    public void saveCurrentWork() {
        if (view.isTraining()) {
                    view.displayAPrompt("Unable to save work in Training Mode");
                } else {
        view.displayAPrompt("Your work has been successfully saved.");
        }
    }

    public void changeMode() {
        boolean trainingMode = view.chooseYourMode();
        service.setMode(trainingMode);

        if (trainingMode) {
            view.displayAPrompt("You are now in training mode.");
        } else {
            view.displayAPrompt("You are now in production mode.");
        }
    }
}
