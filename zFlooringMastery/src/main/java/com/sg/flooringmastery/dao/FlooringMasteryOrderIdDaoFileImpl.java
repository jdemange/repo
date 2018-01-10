/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.OrderId;
import com.sg.flooringmastery.dto.StateTaxRate;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryOrderIdDaoFileImpl implements FlooringMasteryOrderIdDao {
    private boolean training = false;
    public static final String LATEST_ID = "latestId.txt";
    public static final String DELIMITER = ",";

    @Override
    public OrderId getId(String currentFile) throws FlooringMasteryPersistenceException {
        return loadId(currentFile);

    }

    @Override
    public String setId(OrderId newId, String currentFile) throws FlooringMasteryPersistenceException {
        writeId(newId, currentFile);
        return "The ID file has been updated with the latest number.";
        
    }

    private OrderId loadId(String currentFile) throws FlooringMasteryPersistenceException {
        if (isTraining()){
            int intRecentId = 0;
            OrderId mostRecentId = new OrderId(intRecentId);
            mostRecentId.setIdNumber(intRecentId);
            return mostRecentId;
        }
        
        
        Scanner scanner = null;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(currentFile)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException("We are unable to access our ID"
                    + " generation inforamtion. Please contact the help desk before moving forward.", e);

        }

        String currentLine;
        String[] currentTokens;
        OrderId mostRecentId = null;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            String stringRecentId = currentTokens[0];
            int intRecentId = Integer.parseInt(stringRecentId);

            mostRecentId = new OrderId(intRecentId);
            mostRecentId.setIdNumber(intRecentId);

        }
        return mostRecentId;
    }
    
     private void writeId(OrderId latestId, String currentFile) throws FlooringMasteryPersistenceException {

        PrintWriter out;
        if (isTraining()){
            return;
        }

        try {
            out = new PrintWriter(new FileWriter(currentFile));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("Could not save ID information.", e);
        }   
            out.println(
                    Integer.toString(latestId.getIdNumber()) + DELIMITER); 

            out.flush();
        
        out.close();
    }

    public boolean isTraining() {
        return training;
    }

    public void setTraining(boolean training) {
        this.training = training;
    }
     
}
