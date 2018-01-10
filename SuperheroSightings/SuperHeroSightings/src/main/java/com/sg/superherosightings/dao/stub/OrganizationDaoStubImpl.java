/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao.stub;

import com.sg.superherosightings.dao.OrganizationDao;
import com.sg.superherosightings.model.Organization;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class OrganizationDaoStubImpl implements OrganizationDao{
    
   
    

    @Override
    public Organization addOrganization(Organization org) {
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
        
        return org1;
    }

    @Override
    public Organization updateOrganization(Organization org) {
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
        
        return org1;
    }

    @Override
    public void deleteOrganizationById(int id) {
        if (id ==1){
            
        }else{
            throw new UnsupportedOperationException("Your ID is wrong.");
        }
        
    }

    @Override
    public Organization getOrganizationById(int id) {
        if (id ==1){
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
        
        return org1;
        }else{
            throw new UnsupportedOperationException("Your ID is wrong.");
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        List<Organization> orgList = new ArrayList<>();
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
        
        
        orgList.add(org1);
        return orgList;
    }

}
