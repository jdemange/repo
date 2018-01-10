/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.advice;

import com.sg.flooringmastery.dao.FlooringMasteryAuditDao;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author apprentice
 */
public class LoggingAdvice {
    FlooringMasteryAuditDao auditDao;
    
    public LoggingAdvice(FlooringMasteryAuditDao auditDao) throws FlooringMasteryPersistenceException{
        this.auditDao = auditDao;
    }
    
     public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try{
            //String exceptionName = ex.getClass().getSimpleName();
            auditDao.writeAuditEntry(auditEntry,  "auditExceptions.txt");
        } catch (FlooringMasteryPersistenceException e){
            System.err.println(
                "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
     
      public void createAuditEntryWithExceptionName(JoinPoint jp, Exception ex) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try{
            String exceptionName = ex.getClass().getSimpleName();
            auditDao.writeAuditEntry(exceptionName + " - " + auditEntry, "auditExceptions.txt");
        } catch (FlooringMasteryPersistenceException e){
            System.err.println(
                "ERROR: Could not create audit entry in LoggingAdvice.");
        }
}
}
