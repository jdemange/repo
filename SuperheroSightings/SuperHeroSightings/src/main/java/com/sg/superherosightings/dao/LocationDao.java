/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface LocationDao {
    public Location addLocation(Location loc);
    
    public void removeLocationById(int id);
    
    public Location updateLocation(Location loc);
    
    public Location getLocationById(int id);
    
    public List<Location> getAllLocations();
}
