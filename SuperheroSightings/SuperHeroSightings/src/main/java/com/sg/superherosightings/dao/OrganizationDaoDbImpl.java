package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Organization;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author apprentice
 */
public class OrganizationDaoDbImpl implements OrganizationDao {

    private JdbcTemplate jt;
   

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jt = jdbcTemplate;
    }
    
    private static final String SQL_INSERT_ORGANIZATION
            = "insert into organizations (org_name, org_description, street, "
            + "city, state, zip, president, phone) values (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE_ORGANIZATION
            = "update organizations set org_name = ?, org_description = ?, street = ?, "
            + "city = ?, state = ?, zip = ?, president = ?, phone = ? "
            + "where org_id = ?";

    private static final String SQL_DELETE_ORGANIZATION
            = "delete from organizations where org_id = ?";

    private static final String SQL_SELECT_ALL_ORGANIZATIONS
            = "select * from organizations";

    private static final String SQL_SELECT_SINGLE_ORGANIZATION
            = "select * from organizations where org_id = ?";

    
    @Override
    public Organization addOrganization(Organization org) {
        jt.update(SQL_INSERT_ORGANIZATION,
                org.getName(),
                org.getDescription(),
                org.getStreet(),
                org.getCity(),
                org.getState(),
                org.getZip(),
                org.getPresident(),
                org.getPhone());

        org.setId(jt.queryForObject("select LAST_INSERT_ID()", Integer.class));

        return org;
    }

    @Override
    public Organization updateOrganization(Organization org) {
        jt.update(SQL_UPDATE_ORGANIZATION,
                org.getName(),
                org.getDescription(),
                org.getStreet(),
                org.getCity(),
                org.getState(),
                org.getZip(),
                org.getPresident(),
                org.getPhone(),
                org.getId());
        
        return org;
    }

    @Override
    public void deleteOrganizationById(int id) {
        jt.update(SQL_DELETE_ORGANIZATION, id);
    }

    @Override
    public Organization getOrganizationById(int id) {
        try{
            return jt.queryForObject(SQL_SELECT_SINGLE_ORGANIZATION, 
                    new OrganizationMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return jt.query(SQL_SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());
     }

    
    
        private static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization org = new Organization();
            org.setId(rs.getInt("org_id"));
            org.setName(rs.getString("org_name"));
            org.setDescription(rs.getString("org_description"));
            org.setStreet(rs.getString("street"));
            org.setCity(rs.getString("city"));
            org.setState(rs.getString("state"));
            org.setZip(rs.getString("zip"));
            org.setPresident(rs.getString("president"));
            org.setPhone(rs.getString("phone"));
            return org;
        }
    }
}
