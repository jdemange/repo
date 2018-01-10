/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demange.touchcount.service;

import com.demange.touchcount.model.Audit;
import com.demange.touchcount.model.TempAudit;
import java.util.List;

/**
 *
 * @author jocel
 */
public interface AuditServiceLayer {
    
    public Audit addAudit(TempAudit audit);
    
    public List<Audit> getAudtsByUserId(int userId);
    
    public void deleteAudit(int auditId);
    
}
