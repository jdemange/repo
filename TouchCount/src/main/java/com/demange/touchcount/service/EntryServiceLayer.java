/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demange.touchcount.service;

import com.demange.touchcount.model.Entry;
import com.demange.touchcount.model.TempEntry;
import java.util.List;

/**
 *
 * @author jocel
 */
public interface EntryServiceLayer {
    
    public Entry addEntry(TempEntry temp);
    
    public List<Entry> getEntriesByAuditId(int auditId);
    
    public void deleteEntry(int entryId);
    
    public Entry getEntryById(int entryId);
    
    public List<Entry> getEntriesForExcel(int auditId);
}
