/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.HeroToOrganizationRelation;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Picture;
import com.sg.superherosightings.model.Sighting;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface HeroSightingsService {
    public Sighting addSighting(int heroId, int locId, LocalDateTime dateTime);
    
    public List<Sighting> getHerosSightedAtLocation(int locId);
    
    public Hero getHero(int heroId);
    
    public List<Sighting> getSightingsForDate(LocalDateTime dateTime);
    
    public List<Sighting> getTenSightings();
    
    public List<Sighting> getAllSightings();
    
    public Sighting getSightingById(int id);
    
    public Organization getOrganization(int orgId);    
    
    public List<Organization> getAllOrganizations();
    
    public Hero populateHeroInformation(Hero hero);
    
    public Organization populateOrganizationInfo(Organization org);
    
    //HERO CRUD//
    
    public Hero addHero(Hero hero);
    
    public Hero updateHero(Hero hero);
    
    public List<Hero> getAllHeroes();
    
    public void deleteHero(int heroId);
    
   ///Organization CRUD//
    
    public Organization addOrganization(Organization org);
    
    public Organization updateOrganization(Organization org);
    
    public void deleteOrganization(int orgId);
    
    ///LOCATION CRUD//
    
    public Location addLocation(Location loc);
    
    public Location updateLocation(Location loc);
    
    public Location getLocation(int locId);
    
    public void deleteLocation(int locId);
    
    public List<Location> getAllLocations();
    
    
    ///RELATION CRUD
    public HeroToOrganizationRelation addRelationship(int heroId, int orgId);
    
    public void removeRelationship(int heroId, int orgId);
    
    ///SIGHTING CRUD
    public Sighting updateSighting(Sighting sighting);
    
    public void deleteSighting(int sightId);
    
    ///PICTURE STUFF
     public Picture addPicture(Picture picture);
    
    public void deletePicture (int pictureId);
    
    public Picture getPictureById(int pictureId);
    
    public List<Picture> getAllPictures();
    
}
