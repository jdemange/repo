/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao.stub;

import com.sg.superherosightings.dao.HeroToOrganizationRelationDao;
import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.HeroToOrganizationRelation;
import com.sg.superherosightings.model.Organization;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class HeroToOrganizationRelationDaoStubImpl implements HeroToOrganizationRelationDao{

    HeroToOrganizationRelation rel = new HeroToOrganizationRelation();
    
    public HeroToOrganizationRelationDaoStubImpl(){
        Hero onlyHero = new Hero();
        onlyHero.setId(1);
        onlyHero.setName("Teddy");
        onlyHero.setDescription("Bear");
        onlyHero.setSuperPower("Cuddling");
        
        Organization org = new Organization();
        org.setId(1);
        org.setName("Test Name");
        org.setDescription("Test Description");
        org.setStreet("Test Street");
        org.setCity("Test City");
        org.setState("Test State");
        org.setZip("Test Zip");
        org.setPresident("Test Pres");
        org.setPhone("TestPhone");
        rel.setHero(onlyHero);
        
        rel.setHero(onlyHero);
        rel.setOrg(org);
        setRel(rel);
        
    }
    
    @Override
    public HeroToOrganizationRelation addRelationship(HeroToOrganizationRelation rel) {
        return getRel();
    }

    @Override
    public void removeRelationship(int heroId, int orgId) {
        if (heroId == 1 && orgId == 1){
            
        }else{
            throw new UnsupportedOperationException("Your IDs don't match.");
        }
    }

    @Override
    public List<HeroToOrganizationRelation> getAllRelationships() {
        List<HeroToOrganizationRelation>  list  = new ArrayList<>();
        list.add(getRel());
        return list;
    }

    @Override
    public List<HeroToOrganizationRelation> getRelationshipsByHero(int heroId) {
        List<HeroToOrganizationRelation>  list  = new ArrayList<>();
        list.add(getRel());
        return list;
    }

    @Override
    public List<HeroToOrganizationRelation> getRelationshipsByOrg(int orgId) {
        List<HeroToOrganizationRelation>  list  = new ArrayList<>();
        list.add(getRel());
        return list;
    }

    @Override
    public HeroToOrganizationRelation getSingleRelationship(int heroId, int orgId) {
        if (heroId == 1 && orgId == 1){
            return getRel();
        }else{
            throw new UnsupportedOperationException("Your IDs don't match.");
        }
    }

    public HeroToOrganizationRelation getRel() {
        return rel;
    }

    public void setRel(HeroToOrganizationRelation rel) {
        this.rel = rel;
    }
    
}
