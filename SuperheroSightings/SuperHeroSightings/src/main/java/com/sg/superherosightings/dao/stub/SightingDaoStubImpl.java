/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao.stub;

import com.sg.superherosightings.dao.SightingDao;
import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class SightingDaoStubImpl implements SightingDao {

    Sighting sighting = new Sighting();

    public SightingDaoStubImpl() {
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

        Hero onlyHero = new Hero();
        onlyHero.setId(1);
        onlyHero.setName("Teddy");
        onlyHero.setDescription("Bear");
        onlyHero.setSuperPower("Cuddling");

        String str = "1990-06-30 12:30:01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        sighting.setId(1);
        sighting.setDateTime(dateTime);
        sighting.setHero(onlyHero);
        sighting.setLocation(onlyLoc);

        setSighting(sighting);
    }

    @Override
    public List<Sighting> returnAllSightings() {
        List<Sighting> sightings = new ArrayList<>();
        sightings.add(getSighting());
        return sightings;
    }

    @Override
    public List<Sighting> returnSightingByLocationId(int locId) {
        List<Sighting> sightings = new ArrayList<>();
        sightings.add(getSighting());
        return sightings;
    }

    @Override
    public List<Sighting> returnSightingByHeroId(int heroId) {
        List<Sighting> sightings = new ArrayList<>();
        sightings.add(getSighting());
        return sightings;
    }

    @Override
    public List<Sighting> returnSightingByDate(LocalDateTime dateTime) {
        List<Sighting> sightings = new ArrayList<>();
        sightings.add(getSighting());
        return sightings;
    }

    @Override
    public Sighting returnSightingById(int sightId) {
        if (sightId == 1){
            return getSighting();
        }else{
        throw new UnsupportedOperationException("Wrong ID.");
        }
    }

    @Override
    public void removeSighting(int sightingId) {
        if (sightingId == 1){
            
        }else{
        throw new UnsupportedOperationException("Wrong ID.");
        }
    }

    @Override
    public Sighting addSighting(Sighting sighting) {
        return getSighting();
    }

    @Override
    public Sighting updateSighting(Sighting sighting) {
        return sighting;
    }

    public Sighting getSighting() {
        return sighting;
    }

    public void setSighting(Sighting sighting) {
        this.sighting = sighting;
    }

    @Override
    public List<Sighting> returnTenSightings() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
