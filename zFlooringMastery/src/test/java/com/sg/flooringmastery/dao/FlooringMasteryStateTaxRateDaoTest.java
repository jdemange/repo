/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.StateTaxRate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class FlooringMasteryStateTaxRateDaoTest {
    
    FlooringMasteryStateTaxRateDao dao = new FlooringMasteryStateTaxRateDaoFileImpl();
    
    public FlooringMasteryStateTaxRateDaoTest() {
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
     * Test of retrieveTaxRates method, of class FlooringMasteryStateTaxRateDao.
     */
    @Test
    public void testRetrieveTaxRates() throws Exception {
        assertEquals(5, dao.retrieveTaxRates("TESTtaxes.txt").size()); 
        
    }

    
}
