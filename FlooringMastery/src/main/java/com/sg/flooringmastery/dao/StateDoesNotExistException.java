/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

/**
 *
 * @author apprentice
 */
public class StateDoesNotExistException extends Exception {

     public StateDoesNotExistException(String message){
        super(message);
    }
    
    public StateDoesNotExistException(String message, Throwable cause){
        super(message, cause);
    }
    
}
