/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.HeroToOrganizationRelation;
import com.sg.superherosightings.model.Organization;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


/**
 *
 * @author apprentice
 */
public class HeroToOrganizationRelationDaoDbImpl implements HeroToOrganizationRelationDao{
      
    private JdbcTemplate jt;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jt = jdbcTemplate;
    }
    
    private static final String SQL_GET_ALL_RELATIONSHIPS
            = "select organizations.org_id, org_name, org_description, street"
            + ", city, state, zip, president, phone, heros.hero_id, hero_name"
            + ", description, super_power"
            + " from organizations"
            + " join hero_organization_connections hoc on organizations.org_id "
            + "= hoc.org_id "
            + "join heros on hoc.hero_id = heros.hero_id";

    private static final String SQL_INSERT_RELATIONSHIP
            = "insert into hero_organization_connections "
            + "(hero_id, org_id) values (?, ?)";
    
    private static final String SQL_REMOVE_RELATIONSHIP
            = "delete from hero_organization_connections where hero_id = ? "
            + "and org_id = ?";
    
    private static final String SQL_GET_RELATIONSHIP_BY_HERO
            = "select organizations.org_id, org_name, org_description, street"
            + ", city, state, zip, president, phone, heros.hero_id, hero_name"
            + ", description, super_power"
            + " from organizations"
            + " join hero_organization_connections hoc on organizations.org_id "
            + "= hoc.org_id "
            + "join heros on hoc.hero_id = heros.hero_id"
            + " where hoc.hero_id = ?";
    
    private static final String SQL_GET_RELATIONSHIP_BY_ORGANIZATION
            = "select organizations.org_id, org_name, org_description, street"
            + ", city, state, zip, president, phone, heros.hero_id, hero_name"
            + ", description, super_power"
            + " from organizations"
            + " join hero_organization_connections hoc on organizations.org_id "
            + "= hoc.org_id "
            + "join heros on hoc.hero_id = heros.hero_id"
            + " where hoc.org_id = ?";
    
    private static final String SQL_GET_SINGLE_RELATIONSHIP
            = "select organizations.org_id, org_name, org_description, street"
            + ", city, state, zip, president, phone, heros.hero_id, hero_name"
            + ", description, super_power"
            + " from organizations"
            + " join hero_organization_connections hoc on organizations.org_id "
            + "= hoc.org_id "
            + "join heros on hoc.hero_id = heros.hero_id"
            + " where hoc.hero_id = ? and hoc.org_id = ?"; 
    
    @Override
    public HeroToOrganizationRelation addRelationship(HeroToOrganizationRelation rel) {
        jt.update(SQL_INSERT_RELATIONSHIP, 
                rel.getHero().getId(), rel.getOrg().getId());
        return rel;
    }

    @Override
    public void removeRelationship(int heroId, int orgId) {
        jt.update(SQL_REMOVE_RELATIONSHIP, heroId, orgId);
    }

    @Override
    public List<HeroToOrganizationRelation> getAllRelationships() {
        return jt.query(SQL_GET_ALL_RELATIONSHIPS, new RelationshipMapper());
    }

    @Override
    public List<HeroToOrganizationRelation> getRelationshipsByHero(int heroId) {
      
            return jt.query(SQL_GET_RELATIONSHIP_BY_HERO, new RelationshipMapper(), heroId);
       
    }

    @Override
    public List<HeroToOrganizationRelation> getRelationshipsByOrg(int orgId) {
        
            return jt.query(SQL_GET_RELATIONSHIP_BY_ORGANIZATION, new RelationshipMapper(), orgId);
       
    }

    @Override
    public HeroToOrganizationRelation getSingleRelationship(int heroId, int orgId) {
        try {
            return jt.queryForObject(SQL_GET_RELATIONSHIP_BY_ORGANIZATION, new RelationshipMapper(), heroId, orgId);
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }
    
     private static final class RelationshipMapper implements RowMapper<HeroToOrganizationRelation> {

        @Override
        public HeroToOrganizationRelation mapRow(ResultSet rs, int i) throws SQLException {
            Organization org = new Organization();
            org.setId(rs.getInt("organizations.org_id"));
            org.setName(rs.getString("org_name"));
            org.setDescription(rs.getString("org_description"));
            org.setStreet(rs.getString("street"));
            org.setCity(rs.getString("city"));
            org.setState(rs.getString("state"));
            org.setZip(rs.getString("zip"));
            org.setPresident(rs.getString("president"));
            org.setPhone(rs.getString("phone"));
            
            Hero hero = new Hero();
            hero.setId(rs.getInt("heros.hero_id"));
            hero.setName(rs.getString("hero_name"));
            hero.setDescription(rs.getString("description"));
            hero.setSuperPower(rs.getString("super_power"));
            
            HeroToOrganizationRelation relation = new HeroToOrganizationRelation();
            relation.setHero(hero);
            relation.setOrg(org);
            return relation;
            
        }
    }
 
}
