/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao.stub;

import com.sg.superherosightings.dao.HeroDao;
import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Picture;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class HeroDaoStubImpl implements HeroDao{

    private Hero onlyHero;
    
    public HeroDaoStubImpl(){
        onlyHero = new Hero();
        onlyHero.setId(1);
        onlyHero.setName("Teddy");
        onlyHero.setDescription("Bear");
        onlyHero.setSuperPower("Cuddling");
        
        setOnlyHero(onlyHero);
        
    }
    @Override
    public Hero addHero(Hero hero) {
        return getOnlyHero();
    }

    @Override
    public void removeHeroById(int heroId) {
        if (heroId == 1){
            
        } else {
            throw new UnsupportedOperationException("Your ID is wrong.");
        }
    }

    @Override
    public List<Hero> getAllHeroes() {
        List<Hero> heroList = new ArrayList<>();
        heroList.add(getOnlyHero());
        return heroList;
    }

    @Override
    public Hero getHeroById(int heroId) {
        if (heroId == 1){
            return getOnlyHero();
        }else
        throw new UnsupportedOperationException("Your Hero does not exist.");
    }

    @Override
    public Hero updateHero(Hero hero) {
        if (hero.getId() != 0
                && hero.getName() != null
                && hero.getDescription() != null
                && hero.getSuperPower() != null
                ){
            return hero;
        } else {
            throw new UnsupportedOperationException("Your Hero is missing pieces.");
        }
    }

    public Hero getOnlyHero() {
        return onlyHero;
    }

    public void setOnlyHero(Hero onlyHero) {
        this.onlyHero = onlyHero;
    }

    @Override
    public Picture addPicture(Picture picture) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletePicture(int pictureId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Picture getPictureById(int pictureId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Picture> getAllPictures() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
