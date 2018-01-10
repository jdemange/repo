/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.HeroToOrganizationRelation;
import com.sg.superherosightings.model.Organization;
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
public class OrganizationDaoDbImplTest {
    
    OrganizationDao dao;
    HeroToOrganizationRelationDao relDao;
    
    public OrganizationDaoDbImplTest() {
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

        dao = ctx.getBean("orgDao", OrganizationDao.class);
        relDao = ctx.getBean("heroToOrgDao", HeroToOrganizationRelationDao.class);
        
         List<HeroToOrganizationRelation> rels = relDao.getAllRelationships();

        for (HeroToOrganizationRelation currentRel : rels) {
            relDao.removeRelationship(currentRel.getHero().getId(), currentRel.getOrg().getId());
        }
        
        List<Organization> orgs = dao.getAllOrganizations();
        
        for (Organization currentOrg : orgs){
            dao.deleteOrganizationById(currentOrg.getId());
        }
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of addOrganization method, of class OrganizationDaoDbImpl.
     */
    @Test
    public void AddGetOrganization() {
        Organization org = new Organization();
        org.setName("Test Name");
        org.setDescription("Test Description");
        org.setStreet("Test Street");
        org.setCity("Test City");
        org.setState("Test State");
        org.setZip("Test Zip");
        org.setPresident("Test Pres");
        org.setPhone("TestPhone");
        
        org = dao.addOrganization(org);
        
        Organization fromDao = dao.getOrganizationById(org.getId());
        assertTrue(compareOrganizations(org, fromDao));
    }
        @Test
    public void testUpdateLocation() {
        Organization org = new Organization();
        org.setName("Test Name");
        org.setDescription("Test Description");
        org.setStreet("Test Street");
        org.setCity("Test City");
        org.setState("Test State");
        org.setZip("Test Zip");
        org.setPresident("President");
        org.setPhone("Phone");
        
        org = dao.addOrganization(org);
        
        org.setName("NewName");
        Organization org2 = dao.updateOrganization(org);
        assertTrue(org2.getName().equals("NewName"));
        assertTrue(org.getName().equals("NewName"));
        assertTrue(org2.getId() == org.getId());
        
    }

    public boolean compareOrganizations(Organization orgA, Organization orgB){
        if (orgA.getId() == orgB.getId() 
                && orgA.getName().equals(orgB.getName())
                && orgA.getDescription().equals(orgB.getDescription())
                && orgA.getStreet().equals(orgB.getStreet())
                && orgA.getCity().equals(orgB.getCity())
                && orgA.getState().equals(orgB.getState())
                && orgA.getZip().equals(orgB.getZip())
                && orgA.getPresident().equals(orgB.getPresident())
                && orgA.getPhone().equals(orgB.getPhone())){
            return true;
        } else {
            return false;
        }
        
    }

    
}
