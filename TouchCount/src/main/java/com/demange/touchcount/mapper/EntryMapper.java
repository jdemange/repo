/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demange.touchcount.mapper;

import com.demange.touchcount.model.Audit;
import com.demange.touchcount.model.Entry;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author jocel
 */
public class EntryMapper implements RowMapper <Entry>{

    @Override
    public Entry mapRow(ResultSet rs, int i) throws SQLException {
        Entry entry = new Entry();
        entry.setEntryId(rs.getInt("entry_id"));
        
        Audit audit = new Audit();
        audit.setAuditId(rs.getInt("audit_id"));
        entry.setAudit(audit);
        
        entry.setMapNumber(rs.getString("map_number"));
        entry.setRoomName(rs.getString("room_Name"));
        entry.setFloorNumber(rs.getString("floor_number"));
        entry.setBaseCode(rs.getString("base_code"));
        entry.setExtendedCode(rs.getString("ext_code"));
        entry.setQuantity(rs.getInt("quantity"));
        entry.setComments(rs.getString("comments"));
        entry.setFixtureHeight(rs.getString("fixture_height"));
        entry.setCeilingHeight(rs.getString("ceiling_height"));
        entry.setRoomType(rs.getString("room_type"));
            
        return entry;
    }
    
}
