/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demange.touchcount.dao;

import com.demange.touchcount.model.Audit;
import com.demange.touchcount.model.Entry;
import com.demange.touchcount.model.User;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author jocel
 */
public class AuditDaoImplTest {
    AuditDao dao;
    EntryDao entryDao;
    
    public AuditDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ac.getBean("AuditDao", AuditDao.class);
        entryDao = ac.getBean("EntryDao", EntryDao.class);
        
        List<Entry> clear1 = entryDao.getAllEntries();
        
        for (Entry current : clear1){
          entryDao.deleteEntry(current.getEntryId());
        }
        
        List<Audit> clear = dao.getAllAudits();
        
        for (Audit current : clear){
            dao.deleteAudit(current.getAuditId());
        }
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void AddGetAudit() {
        Audit audit = new Audit();
        User user = new User();
        user.setUserId(1);
        
        audit.setLocName("EcoEngineering");
        audit.setUser(user);
        
        audit = dao.addAudit(audit);
        Audit fromDB = dao.getAuditById(audit.getAuditId());
        
        assertTrue(compare(audit, fromDB));
        
        audit.setLocName("different");
        fromDB = dao.updateAudit(audit);
        assertTrue(compare(audit, fromDB));
        
        
        Audit audit2 = new Audit();
                
        audit2.setLocName("EcoEngineering");
        audit2.setUser(user);
        
        audit2 = dao.addAudit(audit2);
        
        Audit audit3 = new Audit();
        
        User user3 = new User();
        user3.setUserId(2);
        audit3.setUser(user3);
        
        audit3.setLocName("Home Office");
        audit3 = dao.addAudit(audit3);
        
        List<Audit> getAll = dao.getAllAudits();
        assertEquals(getAll.size(), 3);
        
        List<Audit> byUser = dao.getAuditsByUser(1);
        assertEquals(byUser.size(), 2);
        
        List<Audit> byUser2 = dao.getAuditsByUser(2);
        assertEquals(byUser2.size(), 1);
        
        dao.deleteAudit(audit2.getAuditId());
        
        byUser = dao.getAuditsByUser(1);
        assertEquals(byUser.size(), 1);
        
        dao.deleteAudit(audit3.getAuditId());
        byUser2 = dao.getAuditsByUser(2);
        assertEquals(byUser2.size(), 0);
        
    }

    private boolean compare(Audit a, Audit b){
        return (a.getAuditId() == b.getAuditId()
                && a.getLocName().equals(b.getLocName())
                && a.getUser().getUserId() == b.getUser().getUserId());
    }
    
}
