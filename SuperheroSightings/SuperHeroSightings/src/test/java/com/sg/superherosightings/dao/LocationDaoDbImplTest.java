/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class LocationDaoDbImplTest {
    
    LocationDao dao;
    
    public LocationDaoDbImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        dao = ctx.getBean("locationDao", LocationDao.class);
        
        List<Location> locs = dao.getAllLocations();
        
        for (Location currentLoc : locs){
            dao.removeLocationById(currentLoc.getId());
        }
        
        
    }
    
    @After
    public void tearDown() {
    }



    /**
     * Test of addLocation method, of class LocationDaoDbImpl.
     */
    @Test
    public void AddGetLocation() {
        Location loc = new Location();
        loc.setName("Test Name");
        loc.setDescription("Test Description");
        loc.setStreet("Test Street");
        loc.setCity("Test City");
        loc.setState("Test State");
        loc.setZip("Test Zip");
        loc.setLatitude("Lat");
        loc.setLongitude("Long");
        
        loc = dao.addLocation(loc);
        
        Location fromDao = dao.getLocationById(loc.getId());
        assertTrue(compareLocations(loc, fromDao));
    }



    /**
     * Test of updateLocation method, of class LocationDaoDbImpl.
     */
    @Test
    public void testUpdateLocation() {
        Location loc = new Location();
        loc.setName("Test Name");
        loc.setDescription("Test Description");
        loc.setStreet("Test Street");
        loc.setCity("Test City");
        loc.setState("Test State");
        loc.setZip("Test Zip");
        loc.setLatitude("Lat");
        loc.setLongitude("Long");
        
        loc = dao.addLocation(loc);
        
        loc.setName("NewName");
        Location loc2 = dao.updateLocation(loc);
        assertTrue(loc2.getName().equals(loc.getName()));
        assertTrue(loc2.getId() == loc.getId());
        
    }

    /**
     * Test of getLocationById method, of class LocationDaoDbImpl.
     */
    @Test
    public void testGetLocationById() {
    }
    
    public boolean compareLocations(Location locA, Location locB){
        if (locA.getId() == locB.getId() 
                && locA.getName().equals(locB.getName())
                && locA.getDescription().equals(locB.getDescription())
                && locA.getStreet().equals(locB.getStreet())
                && locA.getCity().equals(locB.getCity())
                && locA.getState().equals(locB.getState())
                && locA.getZip().equals(locB.getZip())
                && locA.getLatitude().equals(locB.getLatitude())
                && locA.getLongitude().equals(locB.getLongitude())){
            return true;
        } else {
            return false;
        }
        
    }
    
}
