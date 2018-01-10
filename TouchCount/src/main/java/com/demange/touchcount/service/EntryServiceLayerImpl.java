/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demange.touchcount.service;

import com.demange.touchcount.dao.EntryDao;
import com.demange.touchcount.model.Audit;
import com.demange.touchcount.model.Entry;
import com.demange.touchcount.model.TempEntry;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jocel
 */
public class EntryServiceLayerImpl implements EntryServiceLayer{
    
    EntryDao entryDao;
    
    @Inject
    public EntryServiceLayerImpl(EntryDao entryDao){
        this.entryDao = entryDao;
    }

    @Override
    public Entry addEntry(TempEntry temp) {
        Entry entry = new Entry();
        
        Audit audit = new Audit();
        audit.setAuditId(temp.getAuditId());
        
        entry.setAudit(audit);
        entry.setMapNumber(temp.getMapNumber());
        entry.setRoomName(temp.getRoomName());
        entry.setFloorNumber(temp.getFloorNumber());
        entry.setBaseCode(temp.getBaseCode());
        entry.setExtendedCode(temp.getExtendedCode());
        entry.setQuantity(temp.getQuantity());
        entry.setComments(temp.getComments());
        entry.setFixtureHeight(temp.getFixtureHeight());
        entry.setCeilingHeight(temp.getCeilingHeight());
        entry.setRoomType(temp.getRoomType());
        
        return entryDao.addEntry(entry);
        
    }

    @Override
    public List<Entry> getEntriesByAuditId(int auditId) {
        return entryDao.getEntriesByAuditId(auditId);
    }

    @Override
    public void deleteEntry(int entryId) {
        entryDao.deleteEntry(entryId);
    }

    @Override
    public Entry getEntryById(int entryId) {
        return entryDao.getEntry(entryId);
    }

    @Override
    public List<Entry> getEntriesForExcel(int auditId) {
        return entryDao.getEntriesForExcelDownload(auditId);
    }
}
