/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Picture;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface HeroDao {
    
    public Hero addHero(Hero hero);
    
    public void removeHeroById(int heroId);
    
    public List<Hero> getAllHeroes();
    
    public Hero getHeroById(int heroId);
    
    public Hero updateHero(Hero hero);
    
    public Picture addPicture(Picture picture);
    
    public void deletePicture (int pictureId);
    
    public Picture getPictureById(int pictureId);
    
    public List<Picture> getAllPictures();
}
