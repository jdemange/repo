/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demange.touchcount.dao;

import com.demange.touchcount.model.Entry;
import java.util.List;

/**
 *
 * @author jocel
 */
public interface EntryDao {
    public Entry addEntry(Entry entry);
    
    public Entry getEntry(int entryId);
    
    public void deleteEntry (int entryId);
    
    public Entry updateEntry (Entry entry);
    
    public List<Entry> getAllEntries();
    
    public List<Entry> getEntriesByAuditId(int auditId);
    
    public List<Entry> getEntriesForExcelDownload(int auditId);
}
