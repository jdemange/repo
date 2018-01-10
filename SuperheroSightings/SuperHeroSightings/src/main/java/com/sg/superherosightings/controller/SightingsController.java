/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.service.HeroSightingsService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping(value = "sightingsController")
public class SightingsController {

    HeroSightingsService service;

    @Inject
    public SightingsController(HeroSightingsService service) {
        this.service = service;
    }

    @RequestMapping(value = "/displaySightingsPage")
    public String displaySightingsPage(Model model) {
        List<Sighting> tenSightings = service.getTenSightings();
        model.addAttribute("tenSightings", tenSightings);

        List<Sighting> sightings = service.getAllSightings();
        model.addAttribute("sightings", sightings);

        return "sightHome";
    }

    @RequestMapping(value = "/displaySightingsForDate", method = RequestMethod.GET)
    public String displaySightingsForDate(Model model, HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("sightId"));
        model.addAttribute("id", id);

        Sighting sight = service.getSightingById(id);
        model.addAttribute("date", sight.getDateTime());

        List<Sighting> sightingsDate = service.getSightingsForDate(sight.getDateTime());
        model.addAttribute("sightingsDate", sightingsDate);

        List<Sighting> tenSightings = service.getTenSightings();
        model.addAttribute("tenSightings", tenSightings);

        List<Sighting> sightings = service.getAllSightings();
        model.addAttribute("sightings", sightings);

        return "sightHome";
    }
    
      @RequestMapping(value = "/displaySightingDetails", method = RequestMethod.GET)
    public String displaySightingDetails(Model model, HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("heroId"));
        model.addAttribute("id", id);
        
        Hero hero = service.getHero(id);
        model.addAttribute("heroName", hero.getName());

        List<Sighting> sightingsDate = hero.getSightings();
        model.addAttribute("sightingsDate", sightingsDate);

        List<Sighting> tenSightings = service.getTenSightings();
        model.addAttribute("tenSightings", tenSightings);

        List<Sighting> sightings = service.getAllSightings();
        model.addAttribute("sightings", sightings);
        
        int ind = 1;
        model.addAttribute("ind", ind);

        return "sightHome";
    }
    

    @RequestMapping(value = "displayAddSighting", method = RequestMethod.GET)
    public String displayAddSighting(Model model) {
        List<Sighting> tenSightings = service.getTenSightings();
        model.addAttribute("tenSightings", tenSightings);

        List<Hero> heroes = service.getAllHeroes();
        model.addAttribute("heroes", heroes);

        List<Location> locations = service.getAllLocations();
        model.addAttribute("locations", locations);
        
          String message = "Add Sighting To Directory";
        model.addAttribute("message", message);
        
        String destination = "addSighting";
        model.addAttribute("destination", destination);

        return "sightAdd";
    }

    @RequestMapping(value = "addSighting", method = RequestMethod.POST)
    public String addSighting(Model model, HttpServletRequest request) {
        int heroId = Integer.parseInt(request.getParameter("heroId"));
        int locId = Integer.parseInt(request.getParameter("locId"));

        String sDate = request.getParameter("daytime");

        LocalDateTime newDateTime = LocalDateTime.parse(sDate);

        Sighting sight = service.addSighting(heroId, locId, newDateTime);
        sight = service.getSightingById(sight.getId());

        List<Sighting> sightingsDate = service.getSightingsForDate(sight.getDateTime());
        model.addAttribute("sightingsDate", sightingsDate);

        List<Sighting> tenSightings = service.getTenSightings();
        model.addAttribute("tenSightings", tenSightings);

        List<Sighting> sightings = service.getAllSightings();
        model.addAttribute("sightings", sightings);
        
      

        return "sightHome";
    }

    @RequestMapping(value = "displayEditSighting", method = RequestMethod.GET)
    public String displayEditSighting(Model model, HttpServletRequest request) {
        List<Sighting> tenSightings = service.getTenSightings();
        model.addAttribute("tenSightings", tenSightings);

        List<Hero> heroes = service.getAllHeroes();
        model.addAttribute("heroes", heroes);

        List<Location> locations = service.getAllLocations();
        model.addAttribute("locations", locations);
        
        int sightId = Integer.parseInt(request.getParameter("sightId"));
        Sighting editSighting = service.getSightingById(sightId);
        model.addAttribute("editSighting", editSighting);

        String message = "Update Sighting In Directory";
        model.addAttribute("message", message);
        
        String destination = "updateSighting";
        model.addAttribute("destination", destination);
        
        return "sightAdd";
    }
    
    @RequestMapping(value = "updateSighting", method = RequestMethod.POST)
    public String updateSighting(Model model, HttpServletRequest request) {
        int heroId = Integer.parseInt(request.getParameter("heroId"));
        int locId = Integer.parseInt(request.getParameter("locId"));

        String sDate = request.getParameter("daytime");

        LocalDateTime newDateTime = LocalDateTime.parse(sDate);
        Hero hero = new Hero();
        hero.setId(heroId);
        
        Location location = new Location();
        location.setId(locId);
        
        Sighting sight = new Sighting();
        sight.setHero(hero);
        sight.setLocation(location);
        sight.setDateTime(newDateTime);
        sight.setId(Integer.parseInt(request.getParameter("sightId")));
        
        
        sight = service.updateSighting(sight);
        sight = service.getSightingById(sight.getId());

        List<Sighting> sightingsDate = service.getSightingsForDate(sight.getDateTime());
        model.addAttribute("sightingsDate", sightingsDate);

        List<Sighting> tenSightings = service.getTenSightings();
        model.addAttribute("tenSightings", tenSightings);

        List<Sighting> sightings = service.getAllSightings();
        model.addAttribute("sightings", sightings);
        
      

        return "sightHome";
    }
    
      @RequestMapping(value = "deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(Model model, HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("sightId"));
        service.deleteSighting(id);
        return "redirect:displaySightingsPage";
                
    }
}
