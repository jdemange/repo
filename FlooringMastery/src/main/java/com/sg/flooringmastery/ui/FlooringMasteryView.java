/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.OrderId;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.StateTaxRate;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryView {

    UserIO io;
    private boolean training = false;

    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }

    public void displayMainMenu() {
        
        if(training){
            io.print("***********************************");
            io.print("         TRAINING MODE");
            io.print("***********************************");
        }
        io.print(" [1] Display Orders");
        io.print(" [2] Add Order");
        io.print(" [3] Edit Order");
        io.print(" [4] Remove Order");
        io.print(" [5] Save Current Work");
        io.print(" [6] Quit");
        io.print(" [7] Change Mode");
    }

    public int getUserIntegerChoiceWithLimits(int min, int max) {
        return io.readInt("\n Please choose one of the options from "
                + " above.", min, max);
    }
    
    public int getUserIntegerChoiceWithoutLimits(String prompt){
        return io.readInt(prompt);
    }

    public void displayAPrompt(String prompt) {
        io.print("");
        io.print(prompt);
    }

    public LocalDate getDateFromUser() {
        boolean isNotValid = true;
        LocalDate requestedDate = null;

        while (isNotValid) {
            try {
                io.print("");
                String stringDate = io.readString("Please enter the date for which you would like to view orders. "
                        + "(MM/DD/YYYY)");
                requestedDate = LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                isNotValid = false;
            } catch (DateTimeParseException e) {
                io.print("I'm sorry the format of your date is incorrect");
            }
        }
        return requestedDate;
    }

    public void displayRequestedOrders(List<Order> ordersList) {
        for (Order currentOrder : ordersList) {
            OrderId currentId = currentOrder.getOrderNumber();
            Product currentProduct = currentOrder.getProductInfo();

            io.print(
                    "Order Number: " + Integer.toString(currentId.getIdNumber()) + " || "
                    + currentOrder.getCustomerName() + " || "
                    + currentProduct.getProductType() + " || "
                    + "$" + currentOrder.getTotalCost().toString());

            io.print("");
        }
    }

    public String bigDecimalToDollarAmount(BigDecimal number) {
        number.setScale(2, RoundingMode.HALF_DOWN);
        return "$" + number.toString();

    }

    ///*******BEGIN PRINTING PROCESS*****///
    public void displayFullOrderDetails(Order order) {
        io.print("");
        if(training){
            io.print("***********************************");
            io.print("         TRAINING MODE");
            io.print("***********************************");
        }
        printOrderNumber(order);
        printCustomerName(order);
        printOrderDate(order);
        printStateAndTaxRate(order);
        printProductInformation(order);
        printArea(order);
        printMaterialCost(order);
        printLaborCost(order);
        printTaxCost(order);
        printTotalCost(order);
        io.print("");
    }

    private void printOrderNumber(Order order) {
        try {
            OrderId currentId = order.getOrderNumber();
            String orderNumber = Integer.toString(currentId.getIdNumber());
            io.print("Order Number: " + orderNumber);
        } catch (NullPointerException e) {
            io.print("Order Number: ");
        }
    }

    private void printCustomerName(Order order) {
        try {
            io.print("Customer: " + order.getCustomerName());
        } catch (NullPointerException e) {
            io.print("Customer: ");
        }

    }

    private void printOrderDate(Order order) {
        try {
            String stringRequestedDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("MM/dd/YYYY"));
            io.print("Order Date: " + stringRequestedDate);
        } catch (NullPointerException e) {
            io.print("Order Date: ");
        }
    }

    private void printStateAndTaxRate(Order order) {
        try {
            StateTaxRate currentRate = order.getStateAndTaxRate();
            io.print("State: " + currentRate.getState());
            io.print("Tax Rate: " + bigDecimalToDollarAmount(currentRate.getTaxRate()));
        } catch (NullPointerException e) {
            io.print("State: ");
            io.print("Tax Rate: ");
        }
    }

    private void printProductInformation(Order order) {
        try {
            Product currentProduct = order.getProductInfo();
            io.print("Flooring Type: " + currentProduct.getProductType());
            io.print("    Material Cost/SqFt: " + bigDecimalToDollarAmount(currentProduct.getMaterialCostPerSQFT()));
            io.print("    Labor Cost/SqFt: " + bigDecimalToDollarAmount(currentProduct.getLaborCostPerSQFT()));
        } catch (NullPointerException e) {
            io.print("Flooring Type: ");
            io.print("    Material Cost/SqFt: ");
            io.print("    Labor Cost/SqFt: ");
        }
    }

    private void printArea(Order order) {
        try {
            BigDecimal number = order.getArea();
            number.setScale(0, RoundingMode.HALF_UP);
            io.print("Area: " + number.toString());
        } catch (NullPointerException e) {
            io.print("Area: ");
        }
    }

    private void printMaterialCost(Order order) {
        try {
            String stringVersion = bigDecimalToDollarAmount(order.getMaterialCost());
            io.print("Total Material Cost: " + stringVersion);
        } catch (NullPointerException e) {
            io.print("Total Material Cost: ");
        }
    }

    private void printLaborCost(Order order) {
        try {
            String stringVersion = bigDecimalToDollarAmount(order.getLaborCost());
            io.print("Total Labor Cost: " + stringVersion);
        } catch (NullPointerException e) {
            io.print("Total Labor Cost: ");
        }
    }

    private void printTaxCost(Order order) {
        try {
            String stringVersion = bigDecimalToDollarAmount(order.getTaxCost());
            io.print("Total Tax: " + stringVersion);
        } catch (NullPointerException e) {
            io.print("Total Tax: ");
        }
    }

    private void printTotalCost(Order order) {
        try {
            String stringVersion = bigDecimalToDollarAmount(order.getTotalCost());
            io.print("Total Project Cost: " + stringVersion);
        } catch (NullPointerException e) {
            io.print("Total Project Cost: ");
        }
    }

    //////********END PRINTINGPROCESS*****////////////////////////////////////////////////
    public Order getCustomerName(Order newOrder, String prompt) {
        boolean edit = false;
        String display = "";
        String customerName;
        boolean keepGoing = true;

        if (newOrder.getCustomerName() != null) {
            edit = true;
            display = newOrder.getCustomerName();
        }

        while (keepGoing) {
            io.print("");
            io.print(prompt);
            customerName = io.readString("Customer Name(" + display + "):");

            if (edit == false && customerName.equals("")) {
                io.print("You must enter a value for the customer name.");
            } else if (edit == true && customerName.equals("")) {
                return newOrder;
            } else {
                newOrder.setCustomerName(customerName);
                keepGoing = false;
            }

        }
        return newOrder;
    }

    public Order addEditDate(String prompt, Order newOrder) {
        boolean isNotValid = true;
        LocalDate providedDate = null;
        LocalDate requestedDate = null;
        boolean edit = false;
        String forPrinting = "MM/DD/YYYY";

        try {
            providedDate = newOrder.getOrderDate();
            forPrinting = providedDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            edit = true;
        } catch (NullPointerException e) {
        }

        while (isNotValid) {

            io.print("");
            io.print(prompt);
            String stringDate = io.readString("Order Date(" + forPrinting + "): ");

            if (stringDate.equals("") && edit == true) {
                return newOrder;
            } else {

                try {
                    requestedDate = LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                    isNotValid = false;
                } catch (DateTimeParseException e) {
                    io.print("I'm sorry the format of your date is incorrect");
                }
                if (isNotValid == false) {
                    newOrder.setOrderDate(requestedDate);
                    return newOrder;
                }
            }
        }

        return newOrder;
    }

    public String getStateName(Order newOrder, String prompt) {
        String state = "";
        String newState = "";
        boolean editMode = false;
        boolean keepGoing = true;
        try {
            state = newOrder.getStateAndTaxRate().getState();
            editMode = true;
        } catch (NullPointerException e) {
        }

        while (keepGoing) {
            io.print("");
            io.print(prompt);
            newState = io.readString("State Abbreviateion (" + state + "):").toUpperCase();

            if (editMode == false && newState.equals("")) {
                io.print("A value for state must me entered.");
            } else if (editMode == true && newState.equals("")) {
                return state;
            } else {
                return newState;
            }
        }
        return state;
    }

    public Order getProductInformation(String prompt, Order newOrder, List<Product> productList) {
        int length = productList.size();
        boolean editMode = false;
        boolean keepGoing = true;
        String productType = "";
        String newProductType = "";
        try {
            productType = newOrder.getProductInfo().getProductType();
            editMode = true;
        } catch (NullPointerException e) {
        }

        while (keepGoing) {
            int i = 1;
            for (Product currentProduct : productList){
                
                io.print("");
                io.printSameLine(" [" + Integer.toString(i) + "] " + currentProduct.getProductType() + " || Labor: ");
                io.printSameLine(bigDecimalToDollarAmount(currentProduct.getLaborCostPerSQFT()) + " || Material: ");
                io.print(bigDecimalToDollarAmount(currentProduct.getMaterialCostPerSQFT()));
                
                i++;
            }
            io.print("");
            io.print(prompt);

            newProductType = io.readString("Product Type (" + productType + "): ");

            if (editMode == false && newProductType.equals("")) {
                io.print("A value for state must me entered.");
            } else if (editMode == true && newProductType.equals("")) {
                return newOrder;
            } else {
                try {
                    int position = Integer.parseInt(newProductType);
                    Product updatedProduct = productList.get(position - 1);
                    newOrder.setProductInfo(updatedProduct);
                    return newOrder;
                } catch (NullPointerException | NumberFormatException
                        | IndexOutOfBoundsException e) {

                }
            }
        }
        return newOrder;
    }

    public Order addEditArea(String prompt, Order newOrder) {
        boolean keepGoing = true;
        boolean edit = false;
        BigDecimal updatedArea = new BigDecimal(0);
        String toPrint = "SQFT";
        String userInput;
        try {
            updatedArea = newOrder.getArea();
            updatedArea.setScale(0, RoundingMode.HALF_UP);
            toPrint = updatedArea.toString();
            edit = true;
        } catch (NullPointerException e) {
        }

        while (keepGoing) {
            io.print("");
            io.print(prompt);
            userInput = io.readString("Area (" + toPrint + "):");

            if (userInput.equals("") && edit == true) {
                return newOrder;
            } else {
                try {
                    updatedArea = new BigDecimal(userInput);
                    keepGoing = false;
                } catch (NumberFormatException e) {
                    io.print("I'm sorry the number you have entered is "
                            + "invalid. Please try again.");
                }
                if (updatedArea.compareTo(new BigDecimal(0)) <= 0) {
                    io.print("Area of the space must be greater than zero. ");
                }
            }
        }
        newOrder.setArea(updatedArea);
        return newOrder;
    }

    public boolean wouldYouLikeToPersist(String prompt) {
        io.print("");
        io.print(prompt);
        int answer = io.readInt(" [1] Yes \n [2] No", 1, 2);
        return answer == 1;
    }

    public Order getOrderFromList(List<Order> ordersList) {
        Order orderToEdit = null;
        int userChoice;
        boolean keepGoing = true;
        
        while (keepGoing){
            
        displayRequestedOrders(ordersList);
        userChoice = getUserIntegerChoiceWithoutLimits("Please select from the "
                    + "orders listed above."); 
        for (Order currentOrder : ordersList){
           
            if (currentOrder.getOrderNumber().getIdNumber() == userChoice){
                    orderToEdit = currentOrder;
                    keepGoing = false;
                }
            }
        }
        return orderToEdit;
    }

    public boolean chooseYourMode() {
        io.print("");
        io.print("In which mode would you like to work?");
        int answer = io.readInt(" [1] Production \n [2] Training", 1, 2);
        setTraining(answer==2);
        return answer == 2;
    }

    public boolean isTraining() {
        return training;
    }

    public void setTraining(boolean training) {
        this.training = training;
    }

    
    
}
