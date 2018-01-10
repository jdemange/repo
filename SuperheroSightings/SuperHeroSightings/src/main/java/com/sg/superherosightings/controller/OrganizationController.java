/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.model.Hero;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.service.HeroSightingsService;
import java.util.ArrayList;
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
@RequestMapping(value = "organizationController")
public class OrganizationController {

    HeroSightingsService service;

    @Inject
    public OrganizationController(HeroSightingsService service) {
        this.service = service;
    }

    @RequestMapping(value = "/displayOrganizationsPage")
    public String displayOrganizationsPage(Model model) {
        List<Sighting> tenSightings = service.getTenSightings();
        model.addAttribute("tenSightings", tenSightings);

        List<Organization> organizations = service.getAllOrganizations();
        model.addAttribute("organizations", organizations);

        return "orgHome";

    }

    @RequestMapping(value = "displayOrganizationDetails", method = RequestMethod.GET)
    public String displayOrganizationDetails(HttpServletRequest request, Model model) {

        List<Sighting> tenSightings = service.getTenSightings();
        model.addAttribute("tenSightings", tenSightings);

        List<Organization> organizations = service.getAllOrganizations();
        model.addAttribute("organizations", organizations);

        String parameter = request.getParameter("orgId");
        int orgId = Integer.parseInt(parameter);
        Organization organization = service.getOrganization(orgId);
        model.addAttribute("organization", organization);

        return "orgHome";

    }

    @RequestMapping(value = "/displayAddOrganization", method = RequestMethod.GET)
    public String displayAddOrganization(Model model) {
        List<Sighting> tenSightings = service.getTenSightings();
        model.addAttribute("tenSightings", tenSightings);

        String buttonText = "Add Organization to Directory";
        model.addAttribute("buttonText", buttonText);

        String destination = "addOrganization";
        model.addAttribute("destination", destination);

        List<Hero> heroes = service.getAllHeroes();
        model.addAttribute("heroes", heroes);

        return "orgAdd";
    }

    @RequestMapping(value = "/addOrganization", method = RequestMethod.POST)
    public String addOrganization(HttpServletRequest request, Model model) {
        Organization organization = new Organization();

        organization.setName(request.getParameter("orgName"));
        organization.setDescription(request.getParameter("orgDescription"));
        organization.setStreet(request.getParameter("orgStreet"));
        organization.setState(request.getParameter("orgState"));
        organization.setCity(request.getParameter("orgCity"));
        organization.setZip(request.getParameter("orgZip"));
        organization.setPresident(request.getParameter("president"));
        organization.setPhone(request.getParameter("phone"));
        organization = service.addOrganization(organization);

        int orgId = service.getOrganization(organization.getId()).getId();

        String[] members = request.getParameterValues("members");
        if (members!= null){
        for (String currentId : members) {
            int heroId = Integer.parseInt(currentId);
            service.addRelationship(heroId, orgId);
        }
        }

        List<Sighting> tenSightings = service.getTenSightings();
        model.addAttribute("tenSightings", tenSightings);

        List<Organization> organizations = service.getAllOrganizations();
        model.addAttribute("organizations", organizations);

        organization = service.getOrganization(organization.getId());
        model.addAttribute("organization", organization);

        String buttonText = "Add Organization to Directory";
        model.addAttribute("buttonText", buttonText);

        return "orgHome";
    }

    @RequestMapping(value = "/displayEditOrganization", method = RequestMethod.GET)
    public String displayEditOrganization(Model model, HttpServletRequest request) {
        List<Sighting> tenSightings = service.getTenSightings();
        model.addAttribute("tenSightings", tenSightings);

        String parameter = request.getParameter("orgId");
        int orgId = Integer.parseInt(parameter);
        Organization organization = service.getOrganization(orgId);
        model.addAttribute("organization", organization);

        String buttonText = "Update Organization Directory";
        model.addAttribute("buttonText", buttonText);

        String destination = "editOrganization";
        model.addAttribute("destination", destination);
        
        
        List<Hero> selectedHeroes = organization.getHeroes();
        model.addAttribute("selectedHeroes", selectedHeroes);

        List<Hero> allHeroes = service.getAllHeroes();

        List<Hero> heroes = new ArrayList<>();

        for (Hero currentHero : allHeroes) {
            for (Hero selHero : selectedHeroes) {
                if (currentHero.getId() != selHero.getId()) {
                    heroes.add(currentHero);
                }
            }
        }
        
        if (selectedHeroes.size() == 0){
            heroes = allHeroes;
        }

        model.addAttribute("heroes", heroes);

        return "orgAdd";
    }

    @RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
    public String editOrganization(HttpServletRequest request, Model model) {
        Organization organization = new Organization();

        organization.setName(request.getParameter("orgName"));
        organization.setDescription(request.getParameter("orgDescription"));
        organization.setStreet(request.getParameter("orgStreet"));
        organization.setState(request.getParameter("orgState"));
        organization.setCity(request.getParameter("orgCity"));
        organization.setZip(request.getParameter("orgZip"));
        organization.setPresident(request.getParameter("president"));
        organization.setPhone(request.getParameter("phone"));
        organization.setId(Integer.parseInt(request.getParameter("id")));
        organization = service.updateOrganization(organization);

        organization = service.getOrganization(organization.getId());
        List<Hero> originalHeroList= organization.getHeroes();
        
        String[] members = request.getParameterValues("members");
        List<Integer> newHeroIds = new ArrayList<>();
        for (String currentId : members) {
            int heroId = Integer.parseInt(currentId);
            newHeroIds.add(heroId);
        }
        
        //firstdeleteAnyrelationships they removed
        for (Hero currentHero : originalHeroList){
            int count = 0;
            for (int newId : newHeroIds){
                if(newId == currentHero.getId()){
                    count++;
                }
                    
            }
            if (count == 0){
                service.removeRelationship(currentHero.getId(), organization.getId());
            }
        }
        organization = service.getOrganization(organization.getId());
        originalHeroList= organization.getHeroes();
        //Then add relationships that don't exist yet
        for (int currentId : newHeroIds){
            int count = 0;
            for (Hero hero : originalHeroList){
                if (currentId == hero.getId()){
                    count++;
                }
            }
            if (count == 0){
                service.addRelationship(currentId, organization.getId());
            }
        }
        
        List<Sighting> tenSightings = service.getTenSightings();
        model.addAttribute("tenSightings", tenSightings);

        List<Organization> organizations = service.getAllOrganizations();
        model.addAttribute("organizations", organizations);

        organization = service.getOrganization(organization.getId());
        model.addAttribute("organization", organization);

        String buttonText = "Add Organization to Directory";
        model.addAttribute("buttonText", buttonText);

        return "orgHome";
    }

    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("orgId"));
        service.deleteOrganization(id);
        return "redirect:displayOrganizationsPage";
    }
}
