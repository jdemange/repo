/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demange.touchcount.dao;

import com.demange.touchcount.model.Audit;
import java.util.List;

/**
 *
 * @author jocel
 */
public interface AuditDao {
    
    public Audit addAudit(Audit audit);
    
    public Audit updateAudit(Audit audit);
    
    public void deleteAudit(int auditId);
    
    public List<Audit> getAllAudits();
    
    public Audit getAuditById(int auditId);
    
    public List<Audit> getAuditsByUser(int userId);
    
}
