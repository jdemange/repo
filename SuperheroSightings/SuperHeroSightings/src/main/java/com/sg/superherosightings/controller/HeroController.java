/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Picture;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.service.HeroSightingsService;
import java.io.File;
import java.sql.Blob;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping(value = "/heroController")
public class HeroController {

    HeroSightingsService service;
    public static final String pictureFolder = "images/";

    @Inject
    public HeroController(HeroSightingsService service) {
        this.service = service;
    }

    @RequestMapping(value = "/displayHeroesPage")
    public String displayHeroesPage(Model model) {
        List<Sighting> tenSightings = service.getTenSightings();
        model.addAttribute("tenSightings", tenSightings);

        List<Hero> heroes = service.getAllHeroes();
        model.addAttribute("heroes", heroes);

        return "heroHome";
    }

    @RequestMapping(value = "/displayHeroDetails", method = RequestMethod.GET)
    public String displayHeroDetails(Model model, HttpServletRequest request) {
        List<Sighting> tenSightings = service.getTenSightings();
        model.addAttribute("tenSightings", tenSightings);

        List<Hero> heroes = service.getAllHeroes();
        model.addAttribute("heroes", heroes);
        
        String parameter = request.getParameter("heroId");
        int heroId = Integer.parseInt(parameter);
        Hero hero = service.getHero(heroId);
        model.addAttribute("hero", hero);
        
        Picture picture = service.getPictureById(heroId);
        model.addAttribute("picture", picture);

        return "heroHome";
    }

  
    
    @RequestMapping(value = "/deleteHero", method = RequestMethod.GET)
    public String deleteHero(HttpServletRequest request) {
       
        
        String parameter = request.getParameter("heroId");
        int heroId = Integer.parseInt(parameter);
        service.deleteHero(heroId);
        

        return "redirect:displayHeroesPage";
    }
    
    @RequestMapping(value = "/displayAddHero")
    public String displayAddHero(Model model) {
        List<Sighting> tenSightings = service.getTenSightings();
        model.addAttribute("tenSightings", tenSightings);

        return "heroAdd";
    }
    
    @RequestMapping(value = "/addHero", method = RequestMethod.POST)
    public String addHero(Model model, HttpServletRequest request,
      @RequestParam(name = "picture", required = false) MultipartFile pictureFile) {
        Hero hero = new Hero();
        String name = request.getParameter("name");
        hero.setName(name);
        
        hero.setDescription(request.getParameter("description"));
        hero.setSuperPower(request.getParameter("superPower"));
        hero = service.addHero(hero);
        
        
        if(!pictureFile.isEmpty()){
            try{
                String savePath = request
                        .getSession()
                        .getServletContext()
                        .getRealPath("/") + pictureFolder;
                File dir = new File(savePath);
                
                if (!dir.exists()){
                    dir.mkdirs();
                }
                
                String filename = pictureFile.getOriginalFilename();
                pictureFile.transferTo(new File(savePath + filename));
                
                
                Picture picture = new Picture();
                picture.setHeroId(hero.getId());
                picture.setFileName(pictureFolder + filename);
                picture.setTitle(request.getParameter("displayTitle"));
                picture = service.addPicture(picture);
                model.addAttribute("picture", picture);
                
            } catch (Exception e) {
                model.addAttribute("errorMsg", "File upload failed: " + 
                        e.getMessage());
                
            }
        }
    
        Picture picture = service.getPictureById(hero.getId());
        model.addAttribute("picture", picture);
        
        hero = service.getHero(hero.getId());
        model.addAttribute("hero", hero);
    
        List<Sighting> tenSightings = service.getTenSightings();
        model.addAttribute("tenSightings", tenSightings);

        List<Hero> heroes = service.getAllHeroes();
        model.addAttribute("heroes", heroes);
        
        return "heroHome";
        
    }
      @RequestMapping(value = "/displayEditHero", method = RequestMethod.GET)
    public String displayEditHero(Model model, HttpServletRequest request) {
        List<Sighting> tenSightings = service.getTenSightings();
        model.addAttribute("tenSightings", tenSightings);

        List<Hero> heroes = service.getAllHeroes();
        model.addAttribute("heroes", heroes);
        
        String parameter = request.getParameter("heroId");
        int heroId = Integer.parseInt(parameter);
        Hero hero = service.getHero(heroId);
        model.addAttribute("hero", hero);

        return "heroEdit";
    }
    
     @RequestMapping(value = "/editHero", method = RequestMethod.POST)
    public String editHero(Model model, HttpServletRequest request,
          @RequestParam("picture") MultipartFile pictureFile) {
        Hero hero = new Hero();
        
        hero.setId(Integer.parseInt(request.getParameter("id")));
        
        String name = request.getParameter("name");
        hero.setName(name);
        
        hero.setDescription(request.getParameter("description"));
        hero.setSuperPower(request.getParameter("superPower"));
        hero = service.updateHero(hero);
        
        
        hero = service.getHero(hero.getId());
        model.addAttribute("hero", hero);
        
         if(!pictureFile.isEmpty()){
            try{
                String savePath = request
                        .getSession()
                        .getServletContext()
                        .getRealPath("/") + pictureFolder;
                File dir = new File(savePath);
                
                if (!dir.exists()){
                    dir.mkdirs();
                }
                
                String filename = pictureFile.getOriginalFilename();
                pictureFile.transferTo(new File(savePath + filename));
                
                Picture picture = new Picture();
                picture.setHeroId(hero.getId());
                picture.setFileName(pictureFolder + filename);
                picture.setTitle(request.getParameter("displayTitle"));
                picture = service.addPicture(picture);
                model.addAttribute("picture", picture);
                
            } catch (Exception e) {
                model.addAttribute("errorMsg", "File upload failed: " + 
                        e.getMessage());
                
            }
        }
         
        Picture picture = service.getPictureById(hero.getId());
        model.addAttribute("picture", picture);
    
        List<Sighting> tenSightings = service.getTenSightings();
        model.addAttribute("tenSightings", tenSightings);

        List<Hero> heroes = service.getAllHeroes();
        model.addAttribute("heroes", heroes);
        
        return "heroHome";
        
    }

}


