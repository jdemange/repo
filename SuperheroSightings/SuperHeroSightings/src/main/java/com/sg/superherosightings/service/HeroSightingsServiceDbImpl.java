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
import com.sg.superherosightings.model.HeroToOrganizationRelation;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Picture;
import com.sg.superherosightings.model.Sighting;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author apprentice
 */
public class HeroSightingsServiceDbImpl implements HeroSightingsService {

    HeroDao heroDao;
    HeroToOrganizationRelationDao relDao;
    LocationDao locDao;
    OrganizationDao orgDao;
    SightingDao sightDao;

    @Inject
    public HeroSightingsServiceDbImpl(HeroDao heroDao,
            HeroToOrganizationRelationDao relDao, LocationDao locDao,
            OrganizationDao orgDao, SightingDao sightDao) {

        this.heroDao = heroDao;
        this.relDao = relDao;
        this.locDao = locDao;
        this.orgDao = orgDao;
        this.sightDao = sightDao;
    }

    @Override
    public Sighting addSighting(int heroId, int locId, LocalDateTime dateTime) {
        Hero hero = heroDao.getHeroById(heroId);
        Location loc = locDao.getLocationById(locId);
        Sighting newSighting = new Sighting();
        newSighting.setDateTime(dateTime);
        newSighting.setHero(hero);
        newSighting.setLocation(loc);

        return sightDao.addSighting(newSighting);
    }

    @Override
    public List<Sighting> getHerosSightedAtLocation(int locId) {
        List<Sighting> sightings = sightDao.returnSightingByLocationId(locId);
        for (Sighting current : sightings) {
            Hero hero = populateHeroInformation(current.getHero());
            current.setHero(hero);
        }
        return sightings;
    }

    @Override
    public Hero getHero(int heroId) {
        Hero hero = heroDao.getHeroById(heroId);
        hero = populateHeroInformation(hero);
        return hero;
    }

    @Override
    public List<Sighting> getSightingsForDate(LocalDateTime dateTime) {
        List<Sighting> sight = sightDao.returnSightingByDate(dateTime);
        for (Sighting current : sight) {
            current.setHero(populateHeroInformation(current.getHero()));
        }
        return sight;
    }

    @Override
    public List<Sighting> getTenSightings() {
        List<Sighting> sight = sightDao.returnTenSightings();
        for (Sighting current : sight) {
            current.setHero(populateHeroInformation(current.getHero()));
        }
        return sight;
    }

    @Override
    public List<Sighting> getAllSightings() {
        List<Sighting> sight = sightDao.returnAllSightings();
        for (Sighting current : sight) {
            current.setHero(populateHeroInformation(current.getHero()));
        }
        return sight;
    }

    @Override
    public Sighting getSightingById(int id) {
        Sighting sight = sightDao.returnSightingById(id);

        sight.setHero(populateHeroInformation(sight.getHero()));

        return sight;
    }

    @Override
    public Organization getOrganization(int orgId) {
        Organization org = orgDao.getOrganizationById(orgId);
        return populateOrganizationInfo(org);
    }

    @Override
    public Hero populateHeroInformation(Hero hero) {
        List<HeroToOrganizationRelation> rels = relDao.getRelationshipsByHero(hero.getId());
        List<Organization> orgs = new ArrayList<>();

        for (HeroToOrganizationRelation current : rels) {
            orgs.add(current.getOrg());
        }
        hero.setOrganizations(orgs);

        List<Sighting> sights = sightDao.returnSightingByHeroId(hero.getId());
        hero.setSightings(sights);

        return hero;

    }

    @Override
    public Organization populateOrganizationInfo(Organization org) {
        List<HeroToOrganizationRelation> rels = relDao.getRelationshipsByOrg(org.getId());
        List<Hero> heroes = new ArrayList<>();

        for (HeroToOrganizationRelation current : rels) {
            Hero hero = current.getHero();
            hero = populateHeroInformation(hero);
            heroes.add(hero);
        }
        org.setHeroes(heroes);
        return org;

    }

    ///////HERO CRUD////////
    @Override
    public Hero addHero(Hero hero) {
        return heroDao.addHero(hero);

    }

    @Override
    public Hero updateHero(Hero hero) {
        return heroDao.updateHero(hero);

    }

    @Override
    public List<Hero> getAllHeroes() {
        return heroDao.getAllHeroes();
    }

    @Override
    public void deleteHero(int heroId) {
        Hero hero = heroDao.getHeroById(heroId);
        hero = populateHeroInformation(hero);

        for (Sighting current : hero.getSightings()) {
            sightDao.removeSighting(current.getId());
        }

        for (Organization current : hero.getOrganizations()) {
            removeRelationship(heroId, current.getId());
        }

        heroDao.removeHeroById(heroId);
    }

    //ORGANIZATION CRUD///
    @Override
    public Organization addOrganization(Organization org) {
        return orgDao.addOrganization(org);

    }

    @Override
    public Organization updateOrganization(Organization org) {
        return orgDao.updateOrganization(org);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return orgDao.getAllOrganizations();
    }

    @Override
    public void deleteOrganization(int orgId) {
        Organization org = orgDao.getOrganizationById(orgId);
        org = populateOrganizationInfo(org);

        for (Hero current : org.getHeroes()) {
            removeRelationship(current.getId(), orgId);
        }

        orgDao.deleteOrganizationById(orgId);
    }

    //LOCATION CRUD///
    @Override
    public Location addLocation(Location loc) {
        return locDao.addLocation(loc);
    }

    @Override
    public Location updateLocation(Location loc) {
        return locDao.updateLocation(loc);
    }

    @Override
    public Location getLocation(int locId) {
        return locDao.getLocationById(locId);
    }

    @Override
    public void deleteLocation(int locId) {
        List<Sighting> sightings = sightDao.returnSightingByLocationId(locId);

        for (Sighting current : sightings) {
            sightDao.removeSighting(current.getId());
        }

        locDao.removeLocationById(locId);
    }

    @Override
    public List<Location> getAllLocations() {
        return locDao.getAllLocations();
    }

    //HERO ORGANIZATION RELATIONS//
    @Override
    public HeroToOrganizationRelation addRelationship(int heroId, int orgId) {
        Hero hero = getHero(heroId);
        Organization org = getOrganization(orgId);
        HeroToOrganizationRelation rel = new HeroToOrganizationRelation();
        rel.setHero(hero);
        rel.setOrg(org);
        return relDao.addRelationship(rel);
    }

    @Override
    public void removeRelationship(int heroId, int orgId) {
        relDao.removeRelationship(heroId, orgId);

    }

    @Override
    public Sighting updateSighting(Sighting sighting) {
        return sightDao.updateSighting(sighting);
    }

    @Override
    public void deleteSighting(int sightId) {
        sightDao.removeSighting(sightId);
    }

    @Override
    public Picture addPicture(Picture picture) {
        return heroDao.addPicture(picture);
    }

    @Override
    public void deletePicture(int pictureId) {
        heroDao.deletePicture(pictureId);
    }

    @Override
    public Picture getPictureById(int pictureId) {
        return heroDao.getPictureById(pictureId);
    }

    @Override
    public List<Picture> getAllPictures() {
        return heroDao.getAllPictures();
    }

}
