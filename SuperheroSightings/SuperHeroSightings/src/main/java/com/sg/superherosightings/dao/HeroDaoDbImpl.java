/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Picture;
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
public class HeroDaoDbImpl implements HeroDao{

    private JdbcTemplate jt;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jt = jdbcTemplate;
    }
 
    


    //PREPARED STATEMENTS
    private static final String SQL_ADD_HERO
            = "insert into `heros` (`hero_name`, `description`, `super_power`)" 
            +"values (?, ?, ?)";
    
    private static final String SQL_REMOVE_HERO
            = "delete from heros where hero_id = ?";
    
    private static final String SQL_UPDATE_HERO
            = "update heros set hero_name = ?, description = ?, super_power = ? "
            + "where hero_id = ?";
    
    private static final String SQL_SELECT_HERO
            = "select * from heros where hero_id = ?";
    
    private static final String SQL_SELECT_ALL_HEROES
            = "select * from heros";
    
    //PHOTO PREPARED STATEMENTS
    private static final String SQL_ADD_PICTURE
            = "insert into `pictures` ( `hero_id`, `title`, `file_name`)"
            + " values (?, ?, ?)";
    
      private static final String SQL_SELECT_PICTURE
            = "select * from pictures where hero_id = ?";
      
    private static final String SQL_REMOVE_PICTURE
            = "delete from pictures where picture_id = ?";
    
    private static final String SQL_UPDATE_PICTURE
            = "update pictures set picutre_id = ?, hero_id = ?, title = ?, file_name = ?";
    
    private static final String SQL_SELECT_ALL_PICTURES
            = "select * from pictures";
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Hero addHero(Hero hero) {
        jt.update(SQL_ADD_HERO, 
                hero.getName(), 
                hero.getDescription(), 
                hero.getSuperPower());
        
        int heroId
                = jt.queryForObject("select LAST_INSERT_ID()", Integer.class);
        hero.setId(heroId);
        return hero;
    }

    @Override
    public void removeHeroById(int heroId) {
        jt.update(SQL_REMOVE_HERO, heroId);
        //*******************************************
        // CHECK ON THIS BECAUSE OF RELATIONSHIPS
        //*******************************************
    }

    @Override
    public List<Hero> getAllHeroes() {
        List<Hero> heroes = jt.query(SQL_SELECT_ALL_HEROES, new HeroMapper());
        return heroes;
    }

    @Override
    public Hero getHeroById(int heroId) {
        try {
            Hero hero = jt.queryForObject(SQL_SELECT_HERO, new HeroMapper(), heroId);
            return hero;
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public Hero updateHero(Hero hero) {
        
        jt.update(SQL_UPDATE_HERO, 
                hero.getName(), 
                hero.getDescription(), 
                hero.getSuperPower(), 
                hero.getId());
        try {
            Hero updatedHero = jt.queryForObject(SQL_SELECT_HERO, new HeroMapper(), hero.getId());
            return updatedHero;
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Picture addPicture(Picture picture) {
           jt.update(SQL_ADD_PICTURE,  
                picture.getHeroId(), 
                picture.getTitle(), 
                picture.getFileName());
        
        int pictureId
                = jt.queryForObject("select LAST_INSERT_ID()", Integer.class);
        picture.setId(pictureId);
        return picture;
    }

    @Override
    public void deletePicture(int pictureId) {
        jt.update(SQL_REMOVE_PICTURE, pictureId);
    }

    @Override
    public Picture getPictureById(int pictureId) {
          try {
            List<Picture> picture = jt.query(SQL_SELECT_PICTURE, new PictureMapper(), pictureId);
            return picture.get(0);
        } catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<Picture> getAllPictures() {
          List<Picture> pictures = jt.query(SQL_SELECT_ALL_PICTURES, new PictureMapper());
        return pictures; 
    }
    
    private static final class HeroMapper implements RowMapper<Hero>{
        
        @Override
        public Hero mapRow(ResultSet rs, int i) throws SQLException{
            
            Hero hero = new Hero();
            hero.setId(rs.getInt("hero_id"));
            hero.setName(rs.getString("hero_name"));
            hero.setDescription(rs.getString("description"));
            hero.setSuperPower(rs.getString("super_power"));
            return hero;
        }
    }
    
    private static final class PictureMapper implements RowMapper<Picture>{
        
        @Override
        public Picture mapRow(ResultSet rs, int i) throws SQLException{
            
            Picture picture = new Picture();
            picture.setId(rs.getInt("picture_id"));
            picture.setHeroId(rs.getInt("hero_id"));
            picture.setTitle(rs.getString("title"));
            picture.setFileName(rs.getString("file_name"));
            
            return picture;
        }
    }
    
}
