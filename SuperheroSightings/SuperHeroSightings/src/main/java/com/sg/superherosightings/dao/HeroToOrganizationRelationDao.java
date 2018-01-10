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

/**
 *
 * @author apprentice
 */
public interface HeroToOrganizationRelationDao {
    
    public HeroToOrganizationRelation addRelationship(HeroToOrganizationRelation rel);
    
    public void removeRelationship(int heroId, int orgId);
    
    public List<HeroToOrganizationRelation> getAllRelationships();
    
    public List<HeroToOrganizationRelation> getRelationshipsByHero(int heroId);
    
    public List<HeroToOrganizationRelation> getRelationshipsByOrg(int orgId);
    
    public HeroToOrganizationRelation getSingleRelationship(int heroId, int orgId);
}
