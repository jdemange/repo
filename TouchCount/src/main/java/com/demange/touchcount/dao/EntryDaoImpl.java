/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demange.touchcount.dao;

import com.demange.touchcount.mapper.EntryMapper;
import com.demange.touchcount.model.Entry;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jocel
 */
public class EntryDaoImpl implements EntryDao{
    private JdbcTemplate jt;
    
    public void setJdbcTemplate(JdbcTemplate jt){
        this.jt = jt;
    }
    
    private static final String SQL_INSERT_ENTRY
            = "insert into entries (audit_id, map_number, room_name, floor_number, "
            + "base_code, ext_code, quantity, "
            + "comments, fixture_height, ceiling_height, room_type) values"
            + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?) ";
    
    private static final String SQL_GET_ENTRY
            = "select * from entries where entry_id = ?";
    
    private static final String SQL_DELETE_ENTRY
            = "delete from entries where entry_id = ?";
    
    private static final String SQL_UPDATE_ENTRY
            = "update entries set "
            + "audit_id = ?, map_number = ?, room_name = ?, floor_number = ?, "
            + "base_code = ?, ext_code = ?, quantity = ?, "
            + "comments = ?, fixture_height = ?, ceiling_height = ?, room_type = ? "
            + "where entry_id = ?";
    
    private static final String SQL_GET_ALL_ENTRIES
            = "select * from entries";
    
    private static final String SQL_GET_ENTRIES_BY_AUDIT_ID
            = "select * from entries where audit_id = ? "
            + "order by map_number DESC, entry_id";
    
    private static final String SQL_GET_ENTRIES_BY_AUDIT_ID_FOR_EXCEL
            = "select * from entries where audit_id = ? "
            + "order by map_number ASC, entry_id";
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Entry addEntry(Entry entry) {
        jt.update(SQL_INSERT_ENTRY, 
                entry.getAudit().getAuditId(),
                entry.getMapNumber(), entry.getRoomName(), entry.getFloorNumber(), 
                entry.getBaseCode(), entry.getExtendedCode(), entry.getQuantity(), 
                entry.getComments(), entry.getFixtureHeight(), entry.getCeilingHeight(), 
                entry.getRoomType());
        
        int lineId = jt.queryForObject("select LAST_INSERT_ID()", Integer.class);
        return getEntry(lineId);
    }

    @Override
    public Entry getEntry(int entryId) {
        return jt.queryForObject(SQL_GET_ENTRY, new EntryMapper(), entryId);
    }

    @Override
    public void deleteEntry(int entryId) {
        jt.update(SQL_DELETE_ENTRY, entryId);
    }

    @Override
    public Entry updateEntry(Entry entry) {
        jt.update(SQL_UPDATE_ENTRY, 
                entry.getAudit().getAuditId(),
                entry.getMapNumber(), entry.getRoomName(), entry.getFloorNumber(), 
                entry.getBaseCode(), entry.getExtendedCode(), entry.getQuantity(), 
                entry.getComments(), entry.getFixtureHeight(), entry.getCeilingHeight(), 
                entry.getRoomType(), entry.getEntryId());
        
        return getEntry(entry.getEntryId());
    }

    @Override
    public List<Entry> getAllEntries() {
        return jt.query(SQL_GET_ALL_ENTRIES, new EntryMapper());
    }

    @Override
    public List<Entry> getEntriesByAuditId(int auditId) {
        return jt.query(SQL_GET_ENTRIES_BY_AUDIT_ID, new EntryMapper(), auditId);
    }

    @Override
    public List<Entry> getEntriesForExcelDownload(int auditId) {
        return jt.query(SQL_GET_ENTRIES_BY_AUDIT_ID_FOR_EXCEL, new EntryMapper(), auditId);
    }
}
