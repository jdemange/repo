/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demange.touchcount.controller;

import com.demange.touchcount.model.TempCode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author jocel
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayHomePage(Model model) {

        return "home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String displayHomePageAgain(Model model) {

        return "home";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String displayAboutPage(Model model) {

        return "about";
    }

    @RequestMapping(value = "/openTheApp", method = RequestMethod.POST)
    @ResponseBody
    public String openTheApp(Model model, @RequestBody TempCode code) throws Exception{
//        String code = request.getParameter("code");
        LocalDateTime expiration = LocalDateTime.of(2017, 5, 16, 12, 00);
        if (code.getCode().equals("513-985-8300") && LocalDateTime.now().isBefore(expiration)){
        return "index";
        } else if (code.getCode().toUpperCase().equals("T44T8")){
            return "index";
        }
                else{
            throw new Exception();
        }
    }

    @RequestMapping(value = "/reallyOpenTheApp", method = RequestMethod.GET)
    public String reallyOpenTheApp(Model model, HttpServletRequest request) {

        return "index";
    }

    @RequestMapping(value = "/customization", method = RequestMethod.GET)
    public String displayCustomizationPage(Model model) {

        return "customization";
    }

    @RequestMapping(value = "/process", method = RequestMethod.GET)
    public String displayProcessPage(Model model) {

        return "process";
    }

    @RequestMapping(value = "/theApp", method = RequestMethod.GET)
    public String displayAppPage(Model model) {

        return "theApp";
    }
}
