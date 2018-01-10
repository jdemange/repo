/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class SightingDaoDbImpl implements SightingDao {

    private JdbcTemplate jt;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jt = jdbcTemplate;
    }

    private static final String SQL_GET_ALL_SIGHTINGS
            = "select locations.loc_id, loc_name, loc_description, street "
            + ", city, state, zip, latitude, longitude, heros.hero_id, hero_name"
            + ", description, super_power, sighting_date, sighting_id"
            + " from locations"
            + " join sightings on sightings.loc_id = locations.loc_id"
            + " join heros on sightings.hero_id = heros.hero_id";
    
    private static final String SQL_GET_TEN_SIGHTINGS
            = "select locations.loc_id, loc_name, loc_description, street "
            + ", city, state, zip, latitude, longitude, heros.hero_id, hero_name"
            + ", description, super_power, sighting_date, sighting_id"
            + " from locations"
            + " join sightings on sightings.loc_id = locations.loc_id"
            + " join heros on sightings.hero_id = heros.hero_id"
            + " order by sighting_date desc limit 10";

    private static final String SQL_INSERT_SIGHTING
            = "insert into sightings (hero_id, loc_id, sighting_date)"
            + " values (?, ?, ?)";

    private static final String SQL_REMOVE_SIGHTING
            = "delete from sightings where sighting_id = ?";

    private static final String SQL_GET_SIGHTING_BY_SIGHTING_ID
            = "select locations.loc_id, loc_name, loc_description, street "
            + ", city, state, zip, latitude, longitude, heros.hero_id, hero_name"
            + ", description, super_power, sighting_date, sighting_id"
            + " from locations"
            + " join sightings on sightings.loc_id = locations.loc_id"
            + " join heros on sightings.hero_id = heros.hero_id"
            + " where sighting_id = ?";

    private static final String SQL_GET_SIGHTINGS_BY_LOCATION_ID
            = "select locations.loc_id, loc_name, loc_description, street "
            + ", city, state, zip, latitude, longitude, heros.hero_id, hero_name"
            + ", description, super_power, sighting_date, sighting_id"
            + " from locations"
            + " join sightings on sightings.loc_id = locations.loc_id"
            + " join heros on sightings.hero_id = heros.hero_id"
            + " where sightings.loc_id = ?";

    private static final String SQL_GET_SIGHTINGS_BY_HERO_ID
            = "select locations.loc_id, loc_name, loc_description, street "
            + ", city, state, zip, latitude, longitude, heros.hero_id, hero_name"
            + ", description, super_power, sighting_date, sighting_id"
            + " from locations"
            + " join sightings on sightings.loc_id = locations.loc_id"
            + " join heros on sightings.hero_id = heros.hero_id"
            + " where sightings.hero_id = ?";
    
    private static final String SQL_GET_SIGHTINGS_BY_DATE
            = "select locations.loc_id, loc_name, loc_description, street "
            + ", city, state, zip, latitude, longitude, heros.hero_id, hero_name"
            + ", description, super_power, sighting_date, sighting_id"
            + " from locations"
            + " join sightings on sightings.loc_id = locations.loc_id"
            + " join heros on sightings.hero_id = heros.hero_id"
            + " where sighting_date between ? and ?";

    private static final String SQL_UPDATE_SIGHTING
            = "update sightings set hero_id = ?, loc_id = ?, sighting_date = ?"
            + " where sighting_id = ?";

    @Override
    public List<Sighting> returnAllSightings() {
        return jt.query(SQL_GET_ALL_SIGHTINGS, new SightingMapper());
    }
    
    @Override
    public List<Sighting> returnTenSightings() {
        return jt.query(SQL_GET_TEN_SIGHTINGS, new SightingMapper());
    }


    @Override
    public Sighting returnSightingById(int sightId) {
        try {

            return jt.queryForObject(SQL_GET_SIGHTING_BY_SIGHTING_ID, new SightingMapper(), sightId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> returnSightingByLocationId(int locId) {

        return jt.query(SQL_GET_SIGHTINGS_BY_LOCATION_ID, new SightingMapper(), locId);

    }

    @Override
    public List<Sighting> returnSightingByHeroId(int heroId) {
        return jt.query(SQL_GET_SIGHTINGS_BY_HERO_ID, new SightingMapper(), heroId);
    }
    
    @Override
    public List<Sighting> returnSightingByDate(LocalDateTime dateTime) {
//        String str = "1990-06-30 12:30:01";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime newDateTime = LocalDateTime.parse(str, formatter);
        
        LocalDateTime endDate = dateTime.plusDays(1);
        
        
        return jt.query(SQL_GET_SIGHTINGS_BY_DATE, new SightingMapper(), Timestamp.valueOf(dateTime), 
        Timestamp.valueOf(endDate));
    }

    @Override
    public void removeSighting(int sightingId) {
        jt.update(SQL_REMOVE_SIGHTING, sightingId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Sighting addSighting(Sighting sighting) {
        jt.update(SQL_INSERT_SIGHTING,
                sighting.getHero().getId(),
                sighting.getLocation().getId(),
                Timestamp.valueOf(sighting.getDateTime()));
        
        sighting.setId(jt.queryForObject("select LAST_INSERT_ID()", Integer.class));

                return sighting;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Sighting updateSighting(Sighting sighting) {
        jt.update(SQL_UPDATE_SIGHTING,
                sighting.getHero().getId(),
                sighting.getLocation().getId(),
                Timestamp.valueOf(sighting.getDateTime()),
                sighting.getId());
        
        Sighting sight = new Sighting();
        sight.setId(sighting.getId());
        
        return sight;
    }

    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Location loc = new Location();
            loc.setId(rs.getInt("locations.loc_id"));
            loc.setName(rs.getString("loc_name"));
            loc.setDescription(rs.getString("loc_description"));
            loc.setStreet(rs.getString("street"));
            loc.setCity(rs.getString("city"));
            loc.setState(rs.getString("state"));
            loc.setZip(rs.getString("zip"));
            loc.setLatitude(rs.getString("latitude"));
            loc.setLongitude(rs.getString("longitude"));

            Hero hero = new Hero();
            hero.setId(rs.getInt("heros.hero_id"));
            hero.setName(rs.getString("hero_name"));
            hero.setDescription(rs.getString("description"));
            hero.setSuperPower(rs.getString("super_power"));

            Sighting sight = new Sighting();
            sight.setHero(hero);
            sight.setLocation(loc);
            sight.setId(rs.getInt("sighting_id"));
            
                      
            sight.setDateTime(rs.getTimestamp("sighting_date").toLocalDateTime());
            

            return sight;
        }

    }
}
