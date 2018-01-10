/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.HeroToOrganizationRelation;
import com.sg.superherosightings.model.Sighting;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
public class HeroDaoDbImplTest {

    HeroDao dao;
    HeroToOrganizationRelationDao relDao;
    SightingDao sightDao;

    public HeroDaoDbImplTest() {
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
        dao = ctx.getBean("heroDao", HeroDao.class);
        relDao = ctx.getBean("heroToOrgDao", HeroToOrganizationRelationDao.class);
        sightDao = ctx.getBean("sightingDao", SightingDao.class);

        List<Sighting> sights = sightDao.returnAllSightings();

        for (Sighting currentSight : sights) {
            sightDao.removeSighting(currentSight.getId());
        }
        
        List<HeroToOrganizationRelation> rels = relDao.getAllRelationships();
        for (HeroToOrganizationRelation currentRel : rels) {
            relDao.removeRelationship(currentRel.getHero().getId(), currentRel.getOrg().getId());
        }

        List<Hero> heroes = dao.getAllHeroes();
        for (Hero currentHero : heroes) {
            dao.removeHeroById(currentHero.getId());
        }

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addHero method, of class HeroDaoDbImpl.
     */
    @Test
    public void testAddGetHero() {
        Hero hero = new Hero();
        hero.setName("Test Hero");
        hero.setDescription("This is a test");
        hero.setSuperPower("Testing");

        Hero fromDao = dao.addHero(hero);
        assertTrue(compareHeroes(fromDao, hero));

        Hero requestedHero = dao.getHeroById(fromDao.getId());
        assertTrue(compareHeroes(requestedHero, fromDao));

    }

    /**
     * Test of removeHeroById method, of class HeroDaoDbImpl.
     */
    @Test
    public void testRemoveHeroById() {
        Hero hero = new Hero();
        hero.setName("Hero To Delete");
        hero.setDescription("Delete test");
        hero.setSuperPower("Deleting");

        hero = dao.addHero(hero);

        Hero fromDao = dao.getHeroById(hero.getId());
        assertTrue(compareHeroes(hero, fromDao));
        dao.removeHeroById(hero.getId());
        assertNull(dao.getHeroById(hero.getId()));

    }

    /**
     * Test of updateHero method, of class HeroDaoDbImpl.
     */
    @Test
    public void testUpdateHero() {
        Hero hero = new Hero();
        hero.setName("Hero To Update");
        hero.setDescription("Update test");
        hero.setSuperPower("Updating");
        hero = dao.addHero(hero);

        String originalName = hero.getName();
        String originalDescription = hero.getDescription();
        String originalSuperPower = hero.getSuperPower();
        int originalId = hero.getId();

        hero.setName("Test1");
        hero.setDescription("Test2");
        hero.setSuperPower("Test3");
        dao.updateHero(hero);

        Hero fromDao = dao.getHeroById(hero.getId());

        assertTrue("Test1".equals(fromDao.getName()));
        assertTrue("Test2".equals(fromDao.getDescription()));
        assertTrue("Test3".equals(fromDao.getSuperPower()));
        assertEquals(originalId, fromDao.getId());
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
}
