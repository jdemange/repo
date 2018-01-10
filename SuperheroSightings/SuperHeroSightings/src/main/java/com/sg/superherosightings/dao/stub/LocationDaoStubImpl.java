/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao.stub;

import com.sg.superherosightings.dao.LocationDao;
import com.sg.superherosightings.model.Location;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class LocationDaoStubImpl implements LocationDao{

    Location onlyLoc = new Location();
    
    public LocationDaoStubImpl(){
        onlyLoc.setId(1);
        onlyLoc.setName("Test Name");
        onlyLoc.setDescription("Test Description");
        onlyLoc.setStreet("Test Street");
        onlyLoc.setCity("Test City");
        onlyLoc.setState("Test State");
        onlyLoc.setZip("Test Zip");
        onlyLoc.setLatitude("Lat");
        onlyLoc.setLongitude("Long");
        
        setOnlyLoc(onlyLoc);
    }
    
    @Override
    public Location addLocation(Location loc) {
        return getOnlyLoc();
    }

    @Override
    public void removeLocationById(int id) {
        if (id == 1){
            
        } else {
            throw new UnsupportedOperationException("Your ID doesn't exist.");
        }
    }

    @Override
    public Location updateLocation(Location loc) {
          return getOnlyLoc();
    }

    @Override
    public Location getLocationById(int id) {
        if (id == 1){
            return getOnlyLoc();
        } else {
            throw new UnsupportedOperationException("Your ID doesn't exist.");
        }
    }

    @Override
    public List<Location> getAllLocations() {
        List<Location> list = new ArrayList<>();
        list.add(getOnlyLoc());
        return list;
    }

    public Location getOnlyLoc() {
        return onlyLoc;
    }

    public void setOnlyLoc(Location onlyLoc) {
        this.onlyLoc = onlyLoc;
    }
    
}
