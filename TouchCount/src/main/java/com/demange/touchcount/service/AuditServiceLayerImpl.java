/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demange.touchcount.service;

import com.demange.touchcount.dao.AuditDao;
import com.demange.touchcount.model.Audit;
import com.demange.touchcount.model.TempAudit;
import com.demange.touchcount.model.User;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jocel
 */
public class AuditServiceLayerImpl implements AuditServiceLayer{
    AuditDao auditDao;
    
    @Inject
    public AuditServiceLayerImpl(AuditDao auditDao){
        this.auditDao = auditDao;
    }
    
    @Override
    public Audit addAudit(TempAudit audit) {
        Audit toAdd = new Audit();
        toAdd.setLocName(audit.getLocName());
        
        User user = new User();
        user.setUserId(audit.getUserId());
        
        toAdd.setUser(user);
        
        return auditDao.addAudit(toAdd);
    }

    @Override
    public List<Audit> getAudtsByUserId(int userId) {
        return auditDao.getAuditsByUser(userId);
    }

    @Override
    public void deleteAudit(int auditId) {
        auditDao.deleteAudit(auditId);
    }
    
}
