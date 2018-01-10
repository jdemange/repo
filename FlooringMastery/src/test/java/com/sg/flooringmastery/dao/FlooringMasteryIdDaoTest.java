/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.OrderId;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryIdDaoTest {
    FlooringMasteryOrderIdDao dao = new FlooringMasteryOrderIdDaoFileImpl();
    
    public FlooringMasteryIdDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class FlooringMasteryOrderIdDao.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetIdGetId() throws Exception {
        String testFile = "TESTlatestId.txt";
        OrderId testId = new OrderId(5);
        dao.setId(testId, testFile);
        
        OrderId newId = dao.getId(testFile);
        
        assertEquals(5 , newId.getIdNumber());
        
    }

    /**
     * Test of setId method, of class FlooringMasteryOrderIdDao.
     * @throws java.lang.Exception
     */
    @Test
    public void testSetId() throws Exception {
    }


    
}
