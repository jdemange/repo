/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demange.touchcount.mapper;

import com.demange.touchcount.model.Audit;
import com.demange.touchcount.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author jocel
 */
public class AuditMapper implements RowMapper <Audit>{

    @Override
    public Audit mapRow(ResultSet rs, int i) throws SQLException {
        Audit audit = new Audit();
        User user = new User();
        
        audit.setAuditId(rs.getInt("audit_id"));
        audit.setLocName(rs.getString("loc_name"));
        
        user.setUserId(rs.getInt("user_id"));
        audit.setUser(user);
        
        return audit;        
    }
    
}
