/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.HeroToOrganizationRelation;
import com.sg.superherosightings.model.Organization;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
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
public class HeroToOrganizationRelationDaoDbImplTest {

    HeroToOrganizationRelationDao dao;
    HeroDao heroDao;
    OrganizationDao orgDao;
    Hero hero = new Hero();
    Organization org = new Organization();

    public HeroToOrganizationRelationDaoDbImplTest() {
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

        dao = ctx.getBean("heroToOrgDao", HeroToOrganizationRelationDao.class);
        heroDao = ctx.getBean("heroDao", HeroDao.class);
        orgDao = ctx.getBean("orgDao", OrganizationDao.class);

        List<HeroToOrganizationRelation> rels = dao.getAllRelationships();

        for (HeroToOrganizationRelation currentRel : rels) {
            dao.removeRelationship(currentRel.getHero().getId(), currentRel.getOrg().getId());
        }

        hero.setName("Test Hero");
        hero.setDescription("This is a test");
        hero.setSuperPower("Testing");

        hero = heroDao.addHero(hero);

        org.setName("Test Name");
        org.setDescription("Test Description");
        org.setStreet("Test Street");
        org.setCity("Test City");
        org.setState("Test State");
        org.setZip("Test Zip");
        org.setPresident("Test Pres");
        org.setPhone("TestPhone");

        org = orgDao.addOrganization(org);

        HeroToOrganizationRelation rel = new HeroToOrganizationRelation();
        rel.setHero(hero);
        rel.setOrg(org);

        dao.addRelationship(rel);
    }

    @After
    public void tearDown() {
    }


    /**
     * Test of addRelationship method, of class
     * HeroToOrganizationRelationDaoDbImpl.
     */
    @Test
    public void getAddRelationship() {

        HeroToOrganizationRelation rel = new HeroToOrganizationRelation();
        rel.setHero(hero);
        rel.setOrg(org);

        dao.addRelationship(rel);

        List<HeroToOrganizationRelation> list = dao.getRelationshipsByHero(hero.getId());

        for (HeroToOrganizationRelation current : list) {
            if (current.getHero().getId() == hero.getId()) {
                assertTrue(compareHeroes(current.getHero(), hero));
                assertTrue(compareOrganizations(current.getOrg(), org));
            }
        }

    }

    /**
     * Test of removeRelationship method, of class
     * HeroToOrganizationRelationDaoDbImpl.
     */
    @Test
    public void testGetAllRemoveRelationship() {
        dao.removeRelationship(hero.getId(), org.getId());
        assertTrue(dao.getAllRelationships().size() == 0);
    }



    /**
     * Test of getRelationshipsByHero method, of class
     * HeroToOrganizationRelationDaoDbImpl.
     */
    @Test
    public void testGetRelationshipsByHero() {
        List<HeroToOrganizationRelation> fromDao = dao.getRelationshipsByHero(hero.getId());
        assertTrue(fromDao.size() == 1);

      List<HeroToOrganizationRelation> fromDao2 = dao.getRelationshipsByHero(0);
      assertTrue(dao.getRelationshipsByHero(0).size() == 0);
    }

    /**
     * Test of getRelationshipsByOrg method, of class
     * HeroToOrganizationRelationDaoDbImpl.
     */
    @Test
    public void testGetRelationshipsByOrg() {
        List<HeroToOrganizationRelation> fromDao = dao.getRelationshipsByOrg(org.getId());
        assertTrue(fromDao.size() == 1);

      List<HeroToOrganizationRelation> fromDao2 = dao.getRelationshipsByOrg(0);
      assertTrue(dao.getRelationshipsByHero(0).size() == 0);
    }

    /**
     * Test of getSingleRelationship method, of class
     * HeroToOrganizationRelationDaoDbImpl.
     */
    @Test
    public void testGetSingleRelationship() {
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
                && orgA.getPhone().equals(orgB.getPhone())) {
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
}
