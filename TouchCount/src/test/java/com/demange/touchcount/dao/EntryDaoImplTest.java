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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author jocel
 */
public class EntryDaoImplTest {
    EntryDao dao;
    AuditDao auditDao;
    
    public EntryDaoImplTest() {
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
        dao = ac.getBean("EntryDao", EntryDao.class);
        auditDao = ac.getBean("AuditDao", AuditDao.class);
        
        List<Entry> start = dao.getAllEntries();
        for (Entry current : start){
            dao.deleteEntry(current.getEntryId());
        }
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddGetAuditLine() {
        Entry line = new Entry();
        
        Audit audit = new Audit();
        audit.setLocName("test");
        User user = new User();
        user.setUserId(1);
        audit.setUser(user);
        audit = auditDao.addAudit(audit);
        
        line.setAudit(audit);
        
        line.setMapNumber("1");
        line.setRoomName("office");
        line.setFloorNumber("1");
        line.setBaseCode("T44T8");
        line.setExtendedCode("R");
        line.setQuantity(3);
        line.setComments("hard to access");
        line.setFixtureHeight("10'");
        line.setCeilingHeight("20'");
        line.setRoomType("HL");
        
        Entry original = dao.addEntry(line);
        Entry getVersion = dao.getEntry(original.getEntryId());
        
        assertTrue(compareLines(original, getVersion));
        
        original.setMapNumber("2");
        original.setRoomName("Office 2");
        original.setFloorNumber("2");
        original.setBaseCode("T48");
        original.setExtendedCode("2R");
        original.setQuantity(6);
        original.setComments("rd to access");
        original.setFixtureHeight("1'");
        original.setCeilingHeight("2'");
        original.setRoomType("HL7");
        
        original = dao.updateEntry(original);
        getVersion = dao.getEntry(original.getEntryId());
        assertTrue(compareLines(original, getVersion));
        
               
        List<Entry> list = dao.getAllEntries();
        int size1 = list.size();
        dao.deleteEntry(list.get(0).getEntryId());
        
        list = dao.getAllEntries();
        int size2 = list.size();
        
        assertEquals(size1 - 1, size2);
        
    }
    
    @Test
    public void testOrder(){
        Entry line = new Entry();
        
        Audit audit = new Audit();
        audit.setLocName("test");
        User user = new User();
        user.setUserId(1);
        audit.setUser(user);
        audit = auditDao.addAudit(audit);
        
        line.setAudit(audit);
        
        line.setMapNumber("1");
        line.setRoomName("Office 3");
        line.setFloorNumber("1");
        line.setBaseCode("T44T8");
        line.setExtendedCode("R");
        line.setQuantity(3);
        line.setComments("hard to access");
        line.setFixtureHeight("10'");
        line.setCeilingHeight("20'");
        line.setRoomType("HL");
        dao.addEntry(line);
        
        Entry line2 = new Entry();
        
        line2.setAudit(audit);
        
        line2.setMapNumber("2b");
        line2.setRoomName("office 4");
        line2.setFloorNumber("1");
        line2.setBaseCode("T44T8");
        line2.setExtendedCode("R");
        line2.setQuantity(3);
        line2.setComments("hard to access");
        line2.setFixtureHeight("10'");
        line2.setCeilingHeight("20'");
        line2.setRoomType("HL");
        dao.addEntry(line2);
        
        Entry line3 = new Entry();
        
        line3.setAudit(audit);
        
        line3.setMapNumber("2");
        line3.setRoomName("Office 5");
        line3.setFloorNumber("1");
        line3.setBaseCode("T44T8");
        line3.setExtendedCode("R");
        line3.setQuantity(3);
        line3.setComments("hard to access");
        line3.setFixtureHeight("10'");
        line3.setCeilingHeight("20'");
        line3.setRoomType("HL");
        dao.addEntry(line3);
        
//        List<Entry> orderedList = dao.getEntriesByAuditId(audit.getAuditId());
//        assertTrue(line.getMapNumber().equals(orderedList.get(2).getMapNumber()));
//        assertTrue(line2.getMapNumber().equals(orderedList.get(0).getMapNumber()));
//        assertTrue(line3.getMapNumber().equals(orderedList.get(1).getMapNumber()));
        
        
    }

    public boolean compareLines(Entry line1, Entry line2){
        return (line1.getEntryId() == line2.getEntryId()
                && line1.getAudit().getAuditId() == line2.getAudit().getAuditId()
                && line1.getMapNumber().equals(line2.getMapNumber())
                && line1.getRoomName().equals(line2.getRoomName())
                && line1.getFloorNumber().equals(line2.getFloorNumber())
                && line1.getBaseCode().equals(line2.getBaseCode())
                && line1.getExtendedCode().equals(line2.getExtendedCode())
                && line1.getQuantity() == line2.getQuantity()
                && line1.getComments().equals(line2.getComments())
                && line1.getFixtureHeight().equals(line2.getFixtureHeight())
                && line1.getCeilingHeight().equals(line2.getCeilingHeight())
                && line1.getRoomType().equals(line2.getRoomType())
                );
    } 
   
}
