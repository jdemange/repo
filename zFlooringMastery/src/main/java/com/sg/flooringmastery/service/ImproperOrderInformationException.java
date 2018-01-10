/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

/**
 *
 * @author apprentice
 */
public class ImproperOrderInformationException extends Exception {
   
    public ImproperOrderInformationException(String message){
        super(message);
    }
    
    public ImproperOrderInformationException(String message, Throwable cause){
        super(message, cause);
    }
    
}
