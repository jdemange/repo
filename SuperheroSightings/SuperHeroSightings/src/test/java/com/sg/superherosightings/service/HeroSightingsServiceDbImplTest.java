/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.dao.HeroDao;
import com.sg.superherosightings.dao.HeroToOrganizationRelationDao;
import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.dao.SightingDao;
import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
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
public class HeroSightingsServiceDbImplTest {

    HeroToOrganizationRelationDao dao;
    HeroDao heroDao;
    OrganizationDao orgDao;
    LocationDao locDao;
    SightingDao sightDao;
    HeroSightingsService service;

    private Sighting sighting;
    private Organization organization;
    private Hero hero;
    private Location location;

    public HeroSightingsServiceDbImplTest() {

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
                = new ClassPathXmlApplicationContext("test-applicationContext_Service.xml");
        dao = ctx.getBean("heroToOrgDaoSTUB", HeroToOrganizationRelationDao.class);
        heroDao = ctx.getBean("heroDaoSTUB", HeroDao.class);
        orgDao = ctx.getBean("orgDaoSTUB", OrganizationDao.class);
        locDao = ctx.getBean("locationDaoSTUB", LocationDao.class);
        sightDao = ctx.getBean("sightingDaoSTUB", SightingDao.class);
        service = ctx.getBean("service", HeroSightingsService.class);

        Location onlyLoc = new Location();
        onlyLoc.setId(1);
        onlyLoc.setName("Test Name");
        onlyLoc.setDescription("Test Description");
        onlyLoc.setStreet("Test Street");
        onlyLoc.setCity("Test City");
        onlyLoc.setState("Test State");
        onlyLoc.setZip("Test Zip");
        onlyLoc.setLatitude("Lat");
        onlyLoc.setLongitude("Long");
        
        setLocation(onlyLoc);

        Hero onlyHero = new Hero();
        onlyHero.setId(1);
        onlyHero.setName("Teddy");
        onlyHero.setDescription("Bear");
        onlyHero.setSuperPower("Cuddling");
        
        setHero(onlyHero);

        String str = "1990-06-30 12:30:01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        sighting = new Sighting();
        sighting.setId(1);
        sighting.setDateTime(dateTime);
        sighting.setHero(onlyHero);
        sighting.setLocation(onlyLoc);
        setSighting(sighting);
        
        Organization org1 = new Organization();
        org1.setId(1);
        org1.setName("Test Name");
        org1.setDescription("Test Description");
        org1.setStreet("Test Street");
        org1.setCity("Test City");
        org1.setState("Test State");
        org1.setZip("Test Zip");
        org1.setPresident("Test Pres");
        org1.setPhone("TestPhone");
        setOrganization(org1);
        

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addSighting method, of class HeroSightingsServiceDbImpl.
     */
    @Test
    public void testAddSighting() {
        Sighting sighting = getSighting();

        Sighting fromDao = service.addSighting(sighting.getHero().getId(), sighting.getLocation().getId(), sighting.getDateTime());
        assertTrue(compareSightings(sighting, fromDao));

    }

    /**
     * Test of getHerosSightedAtLocation method, of class
     * HeroSightingsServiceDbImpl.
     */
    @Test
    public void testGetHerosSightedAtLocation() {
        List<Sighting> sightings = service.getHerosSightedAtLocation(1);
        assertTrue(compareSightings(getSighting(), sightings.get(0)));
        assertTrue(sightings.size() == 1);
    }

    /**
     * Test of getHero method, of class HeroSightingsServiceDbImpl.
     */
    @Test
    public void testGetHero() {
        Hero fromDao = service.getHero(1);
        assertTrue(compareHeroes(getSighting().getHero(), fromDao));
        
        List<Organization> orgList = fromDao.getOrganizations();
        assertTrue(compareOrganizations(orgList.get(0), getOrganization()));
        assertTrue(orgList.size() == 1);
    }

    /**
     * Test of getSightingsForDate method, of class HeroSightingsServiceDbImpl.
     */
    @Test
    public void testGetSightingsForDate() {
        List<Sighting> sightings = service.getSightingsForDate(getSighting().getDateTime());
        assertTrue(compareSightings(getSighting(), sightings.get(0)));
        assertTrue(sightings.size() == 1);
    }

    /**
     * Test of getOrganization method, of class HeroSightingsServiceDbImpl.
     */
    @Test
    public void testGetOrganizationAndAssociatedHeroes() {
        Organization org = service.getOrganization(1);
        assertTrue(compareOrganizations(getOrganization(), org));
        
        Hero fromDao = org.getHeroes().get(0);
        assertTrue(compareHeroes(getHero(), fromDao));
        assertTrue(org.getHeroes().size() == 1);
    }
    
    @Test
    public void addHeroUpdateHero(){
        Hero addedHero = service.addHero(getHero());
        Hero updatedHero = service.updateHero(getHero());
        
        assertTrue(compareHeroes(addedHero, getHero()));
        assertTrue(compareHeroes(updatedHero, getHero()));
        
    }
    
    @Test
    public void addUpdateOrganization(){
        Organization org = service.addOrganization(getOrganization());
        Organization org2 = service.updateOrganization(getOrganization());
        
        assertTrue(compareOrganizations(org, getOrganization()));
        assertTrue(compareOrganizations(org2, getOrganization()));
    }

    @Test
    public void addUpdateLocation(){
        Location loc = service.addLocation(getLocation());
        Location loc2 = service.updateLocation(getLocation());
        
        assertTrue(compareLocations(loc, getLocation()));
        assertTrue(compareLocations(loc2, getLocation()));
    }
    
    @Test
    public void updateSighting(){
        Sighting sight = service.updateSighting(getSighting());
        
        assertTrue(compareSightings(sight, getSighting()));
    }

    public boolean compareOrganizations(Organization orgA, Organization orgB) {
        if (orgA.getId() == orgB.getId()
                && orgA.getName().equals(orgB.getName())
                && orgA.getDescription().equals(orgB.getDescription())
                && orgA.getStreet().equals(orgB.getStreet())
                && orgA.getCity().equals(orgB.getCity())
                && orgA.getState().equals(orgB.getState())
                && orgA.getZip().equals(orgB.getZip())
                && orgA.getPresident().equals(orgB.getPresident())
                && orgA.getPhone().equals(orgB.getPhone())
                ) {
            return true;
        } else {
            return false;
        }

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

    public boolean compareSightings(Sighting sightA, Sighting sightB) {
        if (sightA.getDateTime().equals(sightB.getDateTime())
                && sightA.getId() == sightB.getId()
                && compareLocations(sightA.getLocation(), sightB.getLocation()) == true
                && compareHeroes(sightA.getHero(), sightB.getHero()) == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean compareLocations(Location locA, Location locB) {
        if (locA.getId() == locB.getId()
                && locA.getName().equals(locB.getName())
                && locA.getDescription().equals(locB.getDescription())
                && locA.getStreet().equals(locB.getStreet())
                && locA.getCity().equals(locB.getCity())
                && locA.getState().equals(locB.getState())
                && locA.getZip().equals(locB.getZip())
                && locA.getLatitude().equals(locB.getLatitude())
                && locA.getLongitude().equals(locB.getLongitude())) {
            return true;
        } else {
            return false;
        }

    }

    public Sighting getSighting() {
        return sighting;
    }

    public void setSighting(Sighting sighting) {
        this.sighting = sighting;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
