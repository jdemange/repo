/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Organization;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface OrganizationDao {
    
    public Organization addOrganization(Organization org);
    
    public Organization updateOrganization(Organization org);
    
    public void deleteOrganizationById(int id);
    
    public Organization getOrganizationById(int id);
    
    public List<Organization> getAllOrganizations();
}
