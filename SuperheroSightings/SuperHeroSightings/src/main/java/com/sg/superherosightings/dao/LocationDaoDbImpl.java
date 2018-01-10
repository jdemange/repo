/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class LocationDaoDbImpl implements LocationDao {

    private JdbcTemplate jt;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jt = jdbcTemplate;
    }

    private static final String SQL_INSERT_LOCATION
            = "insert into locations (loc_name, loc_description, street, "
            + "city, state, zip, latitude, longitude) values (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE_LOCATION
            = "update locations set loc_name = ?, loc_description = ?, street = ?, "
            + "city = ?, state = ?, zip = ?, latitude = ?, longitude = ? "
            + "where loc_id = ?";

    private static final String SQL_DELETE_LOCATION
            = "delete from locations where loc_id = ?";

    private static final String SQL_SELECT_ALL_LOCATIONS
            = "select * from locations";

    private static final String SQL_SELECT_SINGLE_LOCATION
            = "select * from locations where loc_id = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Location addLocation(Location loc) {

        jt.update(SQL_INSERT_LOCATION,
                loc.getName(),
                loc.getDescription(),
                loc.getStreet(),
                loc.getCity(),
                loc.getState(),
                loc.getZip(),
                loc.getLatitude(),
                loc.getLongitude());

        loc.setId(jt.queryForObject("select LAST_INSERT_ID()", Integer.class));

        return loc;

    }

    @Override
    public void removeLocationById(int id) {
        jt.update(SQL_DELETE_LOCATION, id);
    }

    @Override
    public List<Location> getAllLocations() {
        List<Location> locationList = jt.query(SQL_SELECT_ALL_LOCATIONS, new LocationMapper());
        return locationList;
    }

    @Override
    public Location updateLocation(Location loc) {

        jt.update(SQL_UPDATE_LOCATION,
                loc.getName(),
                loc.getDescription(),
                loc.getStreet(),
                loc.getCity(),
                loc.getState(),
                loc.getZip(),
                loc.getLatitude(),
                loc.getLongitude(),
                loc.getId());
        
        return loc;
    }

    @Override
    public Location getLocationById(int id) {
        try{
            return jt.queryForObject(SQL_SELECT_SINGLE_LOCATION, new LocationMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location loc = new Location();
            loc.setId(rs.getInt("loc_id"));
            loc.setName(rs.getString("loc_name"));
            loc.setDescription(rs.getString("loc_description"));
            loc.setStreet(rs.getString("street"));
            loc.setCity(rs.getString("city"));
            loc.setState(rs.getString("state"));
            loc.setZip(rs.getString("zip"));
            loc.setLatitude(rs.getString("latitude"));
            loc.setLongitude(rs.getString("longitude"));
            return loc;
        }
    }

}
