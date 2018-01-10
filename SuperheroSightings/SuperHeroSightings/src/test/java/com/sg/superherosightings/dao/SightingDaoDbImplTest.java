/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class SightingDaoDbImplTest {

    SightingDao dao;
    HeroDao heroDao;
    LocationDao locDao;
    Hero hero = new Hero();
    Location loc = new Location();
    LocalDateTime dateTime;

    public SightingDaoDbImplTest() {
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

        dao = ctx.getBean("sightingDao", SightingDao.class);
        heroDao = ctx.getBean("heroDao", HeroDao.class);
        locDao = ctx.getBean("locationDao", LocationDao.class);
        
        List<Sighting> sights = dao.returnAllSightings();
        
        for (Sighting currentSight : sights){
            dao.removeSighting(currentSight.getId());
        }
       
        
        
        hero.setName("Test Hero");
        hero.setDescription("This is a test");
        hero.setSuperPower("Testing");

        hero = heroDao.addHero(hero);

        loc.setName("Test Name");
        loc.setDescription("Test Description");
        loc.setStreet("Test Street");
        loc.setCity("Test City");
        loc.setState("Test State");
        loc.setZip("Test Zip");
        loc.setLatitude("Lat");
        loc.setLongitude("Long");

        loc = locDao.addLocation(loc);

        String str = "1990-06-30 12:30:01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateTime = LocalDateTime.parse(str, formatter);

    }

    @After
    public void tearDown() {
    }


    /**
     * Test of addSighting method, of class SightingDaoDbImpl.
     */
    @Test
    public void AddandGetByIdSighting() {
        Sighting sightA = new Sighting();
        sightA.setHero(hero);
        sightA.setLocation(loc);
        sightA.setDateTime(dateTime);
        
        sightA = dao.addSighting(sightA);
        
        Sighting fromDao = dao.returnSightingById(sightA.getId());
        
        assertTrue(compareHeroes(fromDao.getHero(), sightA.getHero()));
        
    }
    
    @Test
    public void updateSighting() {
        Sighting sightA = new Sighting();
        sightA.setHero(hero);
        sightA.setLocation(loc);
        sightA.setDateTime(dateTime);
        
        sightA = dao.addSighting(sightA);
        
        String str = "1990-04-30 12:30:01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime2 = LocalDateTime.parse(str, formatter);
        
        sightA.setDateTime(dateTime2);
        dao.updateSighting(sightA);
        Sighting sightB = dao.returnSightingById(sightA.getId());
        assertTrue(sightA.getDateTime().equals(sightB.getDateTime()));
    }
    
    @Test
    public void getSightingByLocationAndHero(){
        Sighting sightA = new Sighting();
        sightA.setHero(hero);
        sightA.setLocation(loc);
        sightA.setDateTime(dateTime);
        
        sightA = dao.addSighting(sightA);
        
        List<Sighting> byHero = dao.returnSightingByHeroId(sightA.getHero().getId());
        List<Sighting> byLocation = dao.returnSightingByLocationId(sightA.getLocation().getId());
        
        assertTrue(compareSightings(sightA, byHero.get(0)));
        assertTrue(compareSightings(sightA, byLocation.get(0)));
        assertTrue(compareSightings(byHero.get(0), byLocation.get(0)));
        
        
    }
    @Test
    public void getSightingByDate(){
        Sighting sightA = new Sighting();
        sightA.setHero(hero);
        sightA.setLocation(loc);
        sightA.setDateTime(dateTime);
        
        sightA = dao.addSighting(sightA);
        
        List<Sighting> byDate = dao.returnSightingByDate(sightA.getDateTime());
        assertTrue(compareSightings(sightA, byDate.get(0)));
    }
    
     public boolean compareHeroes(Hero heroA, Hero heroB) {
        if (heroA.getId() == heroB.getId()
                && heroA.getName().equals(heroB.getName())
                && heroA.getDescription().equals(heroB.getDescription())
                && heroA.getSuperPower().equals(heroB.getSuperPower())) {
            return true;
        } else {
            return false;
        }
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
     
     public boolean compareSightings(Sighting sightA, Sighting sightB){
         if (sightA.getDateTime().equals(sightB.getDateTime())
                 && sightA.getId() == sightB.getId()
                 && compareLocations(sightA.getLocation(), sightB.getLocation()) == true
                 && compareHeroes(sightA.getHero(), sightB.getHero()) == true){
             return true;
         } else {
             return false;
         }
     }



}
