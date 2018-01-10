/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.service.HeroSightingsService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {
    HeroSightingsService service;
    
    @Inject
    public HomeController(HeroSightingsService service){
        this.service = service;
    }
    
    @RequestMapping
    public String displayHomePage(Model model){
        List<Sighting> tenSightings = service.getTenSightings();
        
        model.addAttribute("tenSightings", tenSightings);
        return "home";
    }
}
