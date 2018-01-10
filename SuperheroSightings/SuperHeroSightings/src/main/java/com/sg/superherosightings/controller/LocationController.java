/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.service.HeroSightingsService;
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
@RequestMapping(value = "locationController")
public class LocationController {

    HeroSightingsService service;

    @Inject
    public LocationController(HeroSightingsService service) {
        this.service = service;
    }

    @RequestMapping(value = "/displayLocationsPage")
    public String displayLocationsPage(Model model) {
        List<Sighting> tenSightings = service.getTenSightings();
        model.addAttribute("tenSightings", tenSightings);

        List<Location> locations = service.getAllLocations();
        model.addAttribute("locations", locations);
        
        String buttonText = "Add Location to Directory"; 
        model.addAttribute("buttonText", buttonText);

        return "locHome";
    }

    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request) {
        String parameter = request.getParameter("locId");
        int locId = Integer.parseInt(parameter);
        service.deleteLocation(locId);

        return "redirect:displayLocationsPage";
    }

    @RequestMapping(value = "/displayLocationDetails", method = RequestMethod.GET)
    public String displayLocationDetails(HttpServletRequest request, Model model) {
        List<Sighting> tenSightings = service.getTenSightings();
        model.addAttribute("tenSightings", tenSightings);

        List<Location> locations = service.getAllLocations();
        model.addAttribute("locations", locations);

        String parameter = request.getParameter("locId");
        int locId = Integer.parseInt(parameter);
        Location location = service.getLocation(locId);
        model.addAttribute("location", location);
        
        String buttonText = "Add Location to Directory"; 
        model.addAttribute("buttonText", buttonText);
        
        

        return "locHome";
    }

    @RequestMapping(value = "/displayAddLocation", method = RequestMethod.GET)
    public String displayAddLocation(Model model) {
        List<Sighting> tenSightings = service.getTenSightings();
        model.addAttribute("tenSightings", tenSightings);

        String buttonText = "Add Location to Directory"; 
        model.addAttribute("buttonText", buttonText);
        
        String destination = "addLocation";
        model.addAttribute("destination", destination);
        
        return "locAdd";
    }
    
    @RequestMapping(value = "/addLocation", method = RequestMethod.POST)
    public String addLocation(HttpServletRequest request, Model model ){
        Location location = new Location();
        
        location.setName(request.getParameter("locName"));
        location.setDescription(request.getParameter("locDescription"));
        location.setStreet(request.getParameter("locStreet"));
        location.setState(request.getParameter("locState"));
        location.setCity(request.getParameter("locCity"));
        location.setZip(request.getParameter("locZip"));
        location.setLatitude(request.getParameter("latitude"));
        location.setLongitude(request.getParameter("longitude"));
        location = service.addLocation(location);
        
        List<Sighting> tenSightings = service.getTenSightings();
        model.addAttribute("tenSightings", tenSightings);

        List<Location> locations = service.getAllLocations();
        model.addAttribute("locations", locations);

        
        location = service.getLocation(location.getId());
        model.addAttribute("location", location);
        
        String buttonText = "Add Location to Directory"; 
        model.addAttribute("buttonText", buttonText);

        return "locHome";
    }

    @RequestMapping(value = "/displayEditLocation", method = RequestMethod.GET)
    public String displayEditLocation(Model model, HttpServletRequest request){
        List<Sighting> tenSightings = service.getTenSightings();
        model.addAttribute("tenSightings", tenSightings);

        String parameter = request.getParameter("locId");
        int locId = Integer.parseInt(parameter);
        Location location = service.getLocation(locId);
        model.addAttribute("location", location);
        
        String buttonText = "Update Location Directory"; 
        model.addAttribute("buttonText", buttonText);
        
        String destination = "editLocation";
        model.addAttribute("destination", destination);
        
        return "locAdd";
    }
    
    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public String editLocation(HttpServletRequest request, Model model ){
        Location location = new Location();
        
        location.setName(request.getParameter("locName"));
        location.setDescription(request.getParameter("locDescription"));
        location.setStreet(request.getParameter("locStreet"));
        location.setState(request.getParameter("locState"));
        location.setCity(request.getParameter("locCity"));
        location.setZip(request.getParameter("locZip"));
        location.setLatitude(request.getParameter("latitude"));
        location.setLongitude(request.getParameter("longitude"));
        location.setId(Integer.parseInt(request.getParameter("id")));
        location = service.updateLocation(location);
        
        location = service.getLocation(location.getId());
        model.addAttribute("location", location);
        
        List<Sighting> tenSightings = service.getTenSightings();
        model.addAttribute("tenSightings", tenSightings);

        List<Location> locations = service.getAllLocations();
        model.addAttribute("locations", locations);
        
        String buttonText = "Add Location to Directory"; 
        model.addAttribute("buttonText", buttonText);

        return "locHome";
    }
}
