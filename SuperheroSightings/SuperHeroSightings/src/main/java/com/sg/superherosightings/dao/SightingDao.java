/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Sighting;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface SightingDao {
    
    public List<Sighting> returnAllSightings();
    
    public List<Sighting> returnSightingByLocationId(int locId);
    
    public List<Sighting> returnSightingByHeroId(int heroId);
    
    public List<Sighting> returnSightingByDate(LocalDateTime dateTime);
    
    public Sighting returnSightingById(int sightId);
    
    public void removeSighting(int sightingId);
    
    public Sighting addSighting(Sighting sighting);
    
    public Sighting updateSighting(Sighting sighting);
    
    public List<Sighting> returnTenSightings();
}
