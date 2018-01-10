/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demange.touchcount.controller;

import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author jocel
 */
@Controller
@RequestMapping("/optionsController")
public class OptionsController {

    @RequestMapping(value = "/initialChoice", method = RequestMethod.GET)
    @ResponseBody
    public String initialChoice(HttpServletResponse response) {
        return "<div class=\"choices\">\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"T\" name=\"optionsController/length\">Troffer</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"S\" name=\"optionsController/length\">Strip</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"W\" name=\"optionsController/length\">Wrap</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"R\" name=\"optionsController/length\">Recessed</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"C\" name=\"optionsController/canSize\">Can</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"\" name=\"optionsController/highOrLow\">High/Low Bay</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"\" name=\"optionsController/sensorChoices\">Sensor</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"E\" name=\"optionsController/exteriorChoices\">Exterior</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"\" name=\"optionsController/other\">Other</button>\n"
                + "</p>\n"
                + "</div>";
    }

    @RequestMapping(value = "/displayEntrySummary", method = RequestMethod.GET)
    @ResponseBody
    public String displayEntrySummary() {
        return "<div class=\"choices\">\n"
                + "                                <div class=\"card container quantity-details\">\n"
                + "                                    <div class=\"card-content\">\n"
                + "                                        <div class=\"row\">\n"
                + "                                            <div class=\"col-100 tablet-33 card-content-inner\">\n"
                + "                                                <label for=\"ceiling-height\" class=\"color-gray \">Ceiling-Height </label>\n"
                + "                                                <input type=\"text\" name=\"ceiling-height\" id=\"ceiling-height\" class=\"form-control input-text\" value=\"\">\n"
                + "                                            </div>\n"
                + "                                            <div class=\"col-100 tablet-33 card-content-inner\">\n"
                + "                                                <label for=\"fixture-height\" class=\"color-gray\">Fixture-Height </label>\n"
                + "                                                <input type=\"text\" name=\"fixture-height\" id=\"fixture-height\" class=\"form-control input-text\" value=\"\">\n"
                + "                                            </div>\n"
                + "                                            <div class=\"col-100 tablet-33 card-content-inner \">\n"
                + "                                                <label for=\"map-number\" class=\"color-gray \">Quantity </label>\n"
                + "                                                <div class=\"input-group\">\n"
                + "                                                    <span class=\"input-group-btn\">\n"
                + "                                            <button type=\"button\" class=\"btn btn-default btn-number\" disabled=\"disabled \" data-type=\"minus\" data-field=\"quant[3]\">\n"
                + "                                                <span class=\"glyphicon glyphicon-minus \"></span>\n"
                + "                                                    </button>\n"
                + "                                                    </span>\n"
                + "                                                    <input type=\"text\" name=\"quant[3] \" id=\"quantity\" class=\"form-control input-number\" value=\"1\">\n"
                + "                                                    <span class=\"input-group-btn\">\n"
                + "                                            <button type=\"button\" class=\"btn btn-default btn-number\" data-type=\"plus\" data-field=\"quant[3] \">\n"
                + "                                                <span class=\"glyphicon glyphicon-plus\"></span>\n"
                + "                                                    </button>\n"
                + "                                                    </span>\n"
                + "                                                </div>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                        <p class=\"buttons-row\">\n"
                + "                                            <button class=\"button color-custom button-big button-raised\" onclick=\"addEntry()\">Add Entry to Audit</button>\n"
                + "                                        </p>\n"
                + "\n"
                + "                                        <br/>\n"
                + "                                    </div>\n"
                + "                                </div>\n"
                + "                            </div>";
    }

    @RequestMapping(value = "/other", method = RequestMethod.GET)
    @ResponseBody
    public String other(HttpServletResponse response) {
        return "<div class=\"choices\">\n"
                + "                                <div class=\"card container quantity-details\">\n"
                + "                                    <div class=\"card-content\">\n"
                + "                                        <div class=\"row\">\n"
                + "                                            <div class=\"col-100 tablet-33 card-content-inner\">\n"
                + "                                                <label for=\"ceiling-height\" class=\"color-gray \">Ceiling-Height </label>\n"
                + "                                                <input type=\"text\" name=\"ceiling-height\" id=\"ceiling-height\" class=\"form-control input-text\" value=\"\">\n"
                + "                                            </div>\n"
                + "                                            <div class=\"col-100 tablet-33 card-content-inner\">\n"
                + "                                                <label for=\"fixture-height\" class=\"color-gray\">Fixture-Height </label>\n"
                + "                                                <input type=\"text\" name=\"fixture-height\" id=\"fixture-height\" class=\"form-control input-text\" value=\"\">\n"
                + "                                            </div>\n"
                + "                                            <div class=\"col-100 tablet-33 card-content-inner \">\n"
                + "                                                <label for=\"map-number\" class=\"color-gray \">Quantity </label>\n"
                + "                                                <div class=\"input-group\">\n"
                + "                                                    <span class=\"input-group-btn\">\n"
                + "                                            <button type=\"button\" class=\"btn btn-default btn-number\" disabled=\"disabled\" data-type=\"minus\" data-field=\"quant[3]\">\n"
                + "                                                <span class=\"glyphicon glyphicon-minus\"></span>\n"
                + "                                                    </button>\n"
                + "                                                    </span>\n"
                + "                                                    <input type=\"text\" name=\"quant[3]\" id=\"quantity\" class=\"form-control input-number\" value=\"1\">\n"
                + "                                                    <span class=\"input-group-btn\">\n"
                + "                                            <button type=\"button\" class=\"btn btn-default btn-number\" data-type=\"plus\" data-field=\"quant[3]\">\n"
                + "                                                <span class=\"glyphicon glyphicon-plus\"></span>\n"
                + "                                                    </button>\n"
                + "                                                    </span>\n"
                + "                                                </div>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                        <p class=\"buttons-row\">\n"
                + "                                            <button class=\"button color-custom button-big button-raised\" onclick=\"addEntry()\">Add Entry to Audit</button>\n"
                + "                                        </p>\n"
                + "\n"
                + "                                        <br/>\n"
                + "                                    </div>\n"
                + "                                </div>\n"
                + "                            </div>";
    }

    //////TROFFER SECTION////////
    @RequestMapping(value = "/length", method = RequestMethod.GET)
    @ResponseBody
    public String length(HttpServletResponse response) {
        return "<div class=\"choices\">\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"1\" name=\"optionsController/numberOfLamps\">1 foot</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"2\" name=\"optionsController/numberOfLamps\">2 feet</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"3\" name=\"optionsController/numberOfLamps\">3 feet</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"4\" name=\"optionsController/numberOfLamps\">4 feet</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"6\" name=\"optionsController/numberOfLamps\">6 feet</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"8\" name=\"optionsController/numberOfLamps\">8 feet</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"12\" name=\"optionsController/numberOfLamps\">12 feet</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"\" name=\"optionsController/other\">Other</button>\n"
                + "</p>\n"
                + "</div>";
    }

    @RequestMapping(value = "/numberOfLamps", method = RequestMethod.GET)
    @ResponseBody
    public String numberOfLamps(HttpServletResponse response) {
        return "<div class=\"choices\">\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"1\" name=\"optionsController/lampType\">1 LAMP</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"2\" name=\"optionsController/lampType\">2 LAMP</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"3\" name=\"optionsController/lampType\">3 LAMP</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"4\" name=\"optionsController/lampType\">4 Lamp</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"5\" name=\"optionsController/lampType\">5 Lamp</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"6\" name=\"optionsController/lampType\">6 Lamp</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"8\" name=\"optionsController/lampType\">8 lamps</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"9\" name=\"optionsController/lampType\">9 lamps</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"\" name=\"optionsController/other\">Other</button>\n"
                + "</p>\n"
                + "</div>";
    }

    @RequestMapping(value = "/lampType", method = RequestMethod.GET)
    @ResponseBody
    public String lampType(HttpServletResponse response) {
        return "<div class=\"choices\">\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"T5\" name=\"optionsController/additionalOptions\">T5</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"T8\" name=\"optionsController/additionalOptions\">T8</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"T12\" name=\"optionsController/additionalOptions\">T12</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"LED\" name=\"optionsController/additionalOptions\">LED</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"U-6\"\" name=\"optionsController/additionalOptions\">U-6\"</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"U-4\"\" name=\"optionsController/additionalOptions\">U-4\"</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"Biax\" name=\"optionsController/additionalOptions\">Biax</button>\n"
                + "<button class=\"button button-big\" onclick=\"#\" value=\"\" name=\"optionsController/additionalOptions\"></button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"OTHER\" name=\"optionsController/other\">OTHER</button>\n"
                + "</p>\n"
                + "</div>";
    }

    @RequestMapping(value = "/additionalOptions", method = RequestMethod.GET)
    @ResponseBody
    public String additionalOptions(HttpServletResponse response) {
        return "<div class=\"choices\">\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCodeOnly(this)\" value=\"-R\" name=\"\">REFLECTOR</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCodeOnly(this)\" value=\"-S\" name=\"\">SENSOR</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCodeOnly(this)\" value=\"-XS\" name=\"\">EX SENSOR</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCodeOnly(this)\" value=\"-REMOVE\" name=\"\">REMOVE</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCodeOnly(this)\" value=\"-REPLACE\" name=\"\">REPLACE</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCodeOnly(this)\" value=\"-HO\" name=\"\">HO</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCodeOnly(this)\" value=\"-DIM\" name=\"\">DIM</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCodeOnly(this)\" value=\"-28W\" name=\"\">28W</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCodeOnly(this)\" value=\"-25W\" name=\"\">25W</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom button-fill\" onclick=\"nextPageNoCode(this)\" value=\"\" name=\"optionsController/other\">ROOM OVERVIEW</button>\n"
                + "</p>\n"
                + "</div>";
    }

    //////////////////////////SENSOR AREA/////////////////////////////
    @RequestMapping(value = "/sensorChoices", method = RequestMethod.GET)
    @ResponseBody
    public String sensorChoices(HttpServletResponse response) {
        return "<div class=\"choices\">\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"WSD\" name=\"optionsController/additionalSensorOptions\">WSD</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"WV16\" name=\"optionsController/additionalSensorOptions\">WV16</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"HW13\" name=\"optionsController/additionalSensorOptions\">HW13</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"CM6\" name=\"optionsController/additionalSensorOptions\">CM6</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"CM9\" name=\"optionsController/additionalSensorOptions\">CM9</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"CM10\" name=\"optionsController/additionalSensorOptions\">CM10</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"WCM\" name=\"optionsController/additionalSensorOptions\">WCM</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"WWV\" name=\"optionsController/additionalSensorOptions\">WWV</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"WHW\" name=\"optionsController/additionalSensorOptions\">WHW</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"CMRB-6\" name=\"optionsController/additionalSensorOptions\">CMRB-6</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"CM\" name=\"optionsController/additionalSensorOptions\">CM</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"OTHER\" name=\"optionsController/other\">OTHER</button>\n"
                + "</p>\n"
                + "</div>";
    }

    @RequestMapping(value = "/additionalSensorOptions", method = RequestMethod.GET)
    @ResponseBody
    public String additionalSensorOptions(HttpServletResponse response) {
        return "<div class=\"choices\">\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCodeOnly(this)\" value=\"-PDT\" name=\"\">PDT</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCodeOnly(this)\" value=\"-2P\" name=\"\">2P</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCodeOnly(this)\" value=\"-PC\" name=\"\">PC</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCodeOnly(this)\" value=\"-WH\" name=\"\">WHITE</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCodeOnly(this)\" value=\"-IV\" name=\"\">IVORY</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCodeOnly(this)\" value=\"-GY\" name=\"\">GRAY</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCodeOnly(this)\" value=\"-BK\" name=\"\">BLACK</button>\n"
                + "<button class=\"button button-big \" onclick=\"#\" value=\"\" name=\"\"></button>\n"
                + "<button class=\"button button-big \" onclick=\"#\" value=\"\" name=\"\"></button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-lime button-fill\" onclick=\"nextPageNoCode(this)\" value=\"\" name=\"optionsController/other\">ROOM OVERVIEW</button>\n"
                + "</p>\n"
                + "</div>";
    }

    ////////////////////////////////////CAN AREA //////////////////////////
    @RequestMapping(value = "/canSize", method = RequestMethod.GET)
    @ResponseBody
    public String canSize(HttpServletResponse response) {
        return "<div class=\"choices\">\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-0\" name=\"optionsController/numberOfCanLamps\">0 INCHES</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-2\" name=\"optionsController/numberOfCanLamps\">2 INCHES</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-4\" name=\"optionsController/numberOfCanLamps\">4 INCHES</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-6\" name=\"optionsController/numberOfCanLamps\">6 INCHES</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-8\" name=\"optionsController/numberOfCanLamps\">8 INCHES</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-10\" name=\"optionsController/numberOfCanLamps\">10 INCHES</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"-12\" name=\"optionsController/numberOfCanLamps\">12 INCHES</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-24\" name=\"optionsController/numberOfCanLamps\">24 INCHES</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"\" name=\"optionsController/other\">OTHER</button>\n"
                + "</p>\n"
                + "</div>";
    }

    @RequestMapping(value = "/numberOfCanLamps", method = RequestMethod.GET)
    @ResponseBody
    public String numberOfCanLamps(HttpServletResponse response) {
        return "<div class=\"choices\">\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-1L\" name=\"optionsController/lampTypeCan\">1 LAMP</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-2L\" name=\"optionsController/lampTypeCan\">2 LAMP</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-3L\" name=\"optionsController/lampTypeCan\">3 LAMP</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-4L\" name=\"optionsController/lampTypeCan\">4 Lamp</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-5L\" name=\"optionsController/lampTypeCan\">5 Lamp</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-6L\" name=\"optionsController/lampTypeCan\">6 Lamp</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"-8L\" name=\"optionsController/lampTypeCan\">8 lamps</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-9L\" name=\"optionsController/lampTypeCan\">9 lamps</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"\" name=\"optionsController/other\">OTHER</button>\n"
                + "</p>\n"
                + "</div>";
    }

    @RequestMapping(value = "/lampTypeCan", method = RequestMethod.GET)
    @ResponseBody
    public String lampTypeCan(HttpServletResponse response) {
        return "<div class=\"choices\">\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"\" name=\"optionsController/incandescentWattages\">INCANDESCENT</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"\" name=\"optionsController/cflWattages\">CFL</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"\" name=\"optionsController/plWattages\">PL</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"\" name=\"optionsController/parTypes\">PAR</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"\" name=\"optionsController/brTypes\">BR/MR16</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"\" name=\"optionsController/hidWattages\">HID</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"\" name=\"optionsController/ledWattages\">LED</button>\n"
                + "<button class=\"button button-big\" onclick=\"#\" value=\"\" name=\"\"></button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"\" name=\"optionsController/other\">OTHER</button>\n"
                + "</p>\n"
                + "</div>";
    }

    @RequestMapping(value = "/incandescentWattages", method = RequestMethod.GET)
    @ResponseBody
    public String incandescentWattages(HttpServletResponse response) {
        return "<div class=\"choices\">\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-40W-INCAN\" name=\"optionsController/additionalOptions\">40W</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-60W-INCAN\" name=\"optionsController/additionalOptions\">60W</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-75W-INCAN\" name=\"optionsController/additionalOptions\">75W</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-100W-INCAN\" name=\"optionsController/additionalOptions\">100W</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-150W-INCAN\" name=\"optionsController/additionalOptions\">150W</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-200W-INCAN\" name=\"optionsController/additionalOptions\">200W</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-250W-INCAN\" name=\"optionsController/additionalOptions\">250W</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-300W-INCAN\" name=\"optionsController/additionalOptions\">300W</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"\" name=\"optionsController/other\">OTHER</button>\n"
                + "</p>\n"
                + "</div>";
    }

    @RequestMapping(value = "/cflWattages", method = RequestMethod.GET)
    @ResponseBody
    public String cflWattages(HttpServletResponse response) {
        return "<div class=\"choices\">\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-5W-CFL\" name=\"optionsController/additionalOptions\">5 WATT</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-9W-CFL\" name=\"optionsController/additionalOptions\">9 WATT</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-11W-CFL\" name=\"optionsController/additionalOptions\">11 WATT</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-13W-CFL\" name=\"optionsController/additionalOptions\">13 WATT</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-19W-CFL\" name=\"optionsController/additionalOptions\">19 WATT</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-23W-CFL\" name=\"optionsController/additionalOptions\">23 WATT</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-26W-CFL\" name=\"optionsController/additionalOptions\">26 WATT</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-42W-CFL\" name=\"optionsController/additionalOptions\">42 WATT</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"\" name=\"optionsController/other\">OTHER</button>\n"
                + "</p>\n"
                + "</div>";
    }

    @RequestMapping(value = "/plWattages", method = RequestMethod.GET)
    @ResponseBody
    public String plWattages(HttpServletResponse response) {
        return "<div class=\"choices\">\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-9W-PL\" name=\"optionsController/additionalOptions\">9 WATT</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-11W-PL\" name=\"optionsController/additionalOptions\">11 WATT</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-13W-PL\" name=\"optionsController/additionalOptions\">13 WATT</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-18W-PL\" name=\"optionsController/additionalOptions\">18 WATT</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-26W-PL\" name=\"optionsController/additionalOptions\">26 WATT</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-32W-PL\" name=\"optionsController/additionalOptions\">32 WATT</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-42W-PL\" name=\"optionsController/additionalOptions\">42 WATT</button>\n"
                + "<button class=\"button button-big\" onclick=\"#\" value=\"\" name=\"\"></button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"\" name=\"optionsController/other\">OTHER</button>\n"
                + "</p>\n"
                + "</div>";
    }

    @RequestMapping(value = "/parTypes", method = RequestMethod.GET)
    @ResponseBody
    public String parTypes(HttpServletResponse response) {
        return "<div class=\"choices\">\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-25W-PAR16\" name=\"optionsController/additionalOptions\">PAR16</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-45W-PAR20\" name=\"optionsController/additionalOptions\">PAR20</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-65W-PAR30\" name=\"optionsController/additionalOptions\">PAR30</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-100W-PAR36\" name=\"optionsController/additionalOptions\">PAR36</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-120W-PAR38\" name=\"optionsController/additionalOptions\">PAR38</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-150W-PAR46\" name=\"optionsController/additionalOptions\">PAR46</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-200W-PAR56\" name=\"optionsController/additionalOptions\">PAR56</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-250W-PAR64\" name=\"optionsController/additionalOptions\">PAR64</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"\" name=\"optionsController/other\">OTHER</button>\n"
                + "</p>\n"
                + "</div>";
    }

    @RequestMapping(value = "/brTypes", method = RequestMethod.GET)
    @ResponseBody
    public String brTypes(HttpServletResponse response) {
        return "<div class=\"choices\">\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-75W-BR25\" name=\"optionsController/additionalOptions\">BR25</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-65W-BR30\" name=\"optionsController/additionalOptions\">BR30</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-100W-BR38\" name=\"optionsController/additionalOptions\">BR38</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-80W-BR40\" name=\"optionsController/additionalOptions\">BR40</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-20W-MR8\" name=\"optionsController/additionalOptions\">MR8</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"20W-MR11\" name=\"optionsController/additionalOptions\">MR11</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"52W-MR16\" name=\"optionsController/additionalOptions\">MR16</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"20W-MR20\" name=\"optionsController/additionalOptions\">MR20</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"\" name=\"optionsController/other\">OTHER</button>\n"
                + "</p>\n"
                + "</div>";
    }

    /////////////////HIGHBAY / LOWBAY AREA ////////////////
    @RequestMapping(value = "/highOrLow", method = RequestMethod.GET)
    @ResponseBody
    public String highOrLow(HttpServletResponse response) {
        return "<div class=\"choices\">\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"H\" name=\"optionsController/linearOrHid\">HIGH BAY</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"L\" name=\"optionsController/linearOrHid\">LOW BAY</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-big\" onclick=\"#\" value=\"\" name=\"\"></button>\n"
                + "<button class=\"button button-big\" onclick=\"#\" value=\"\" name=\"\"></button>\n"
                + "<button class=\"button button-big\" onclick=\"#\" value=\"\" name=\"\"></button>\n"
                + "</p>\n"
                + "</div>";
    }

    @RequestMapping(value = "/linearOrHid", method = RequestMethod.GET)
    @ResponseBody
    public String linearOrHid(HttpServletResponse response) {
        return "<div class=\"choices\">\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"\" name=\"optionsController/numberOfLamps\">LINEAR</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"\" name=\"optionsController/hidWattages\">HID</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"\" name=\"optionsController/other\">Other</button>\n"
                + "</p>\n"
                + "</div>";
    }

    @RequestMapping(value = "/hidWattages", method = RequestMethod.GET)
    @ResponseBody
    public String hidWattages(HttpServletResponse response) {
        return "<div class=\"choices\">\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-50W\" name=\"optionsController/hidType\">50 WATT</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-70W\" name=\"optionsController/hidType\">70 WATT</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-100W\" name=\"optionsController/hidType\">100 WATT</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-150W\" name=\"optionsController/hidType\">150 WATT</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-200W\" name=\"optionsController/hidType\">200 WATT</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-250W\" name=\"optionsController/hidType\">250 WATT</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-400W\" name=\"optionsController/hidType\">400 WATT</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"-1000W\" name=\"optionsController/hidType\">1000 WATT</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"\" name=\"optionsController/other\">OTHER</button>\n"
                + "</p>\n"
                + "</div>";
    }

    @RequestMapping(value = "/hidType", method = RequestMethod.GET)
    @ResponseBody
    public String hidType(HttpServletResponse response) {
        return "<div class=\"choices\">\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"\" name=\"optionsController/additionalOptions\">MH</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"\" name=\"optionsController/additionalOptions\">PSMH</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"\" name=\"optionsController/additionalOptions\">CMH</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"\" name=\"optionsController/additionalOptions\">HPS</button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"addToCode(this)\" value=\"\" name=\"optionsController/additionalOptions\">LPS</button>\n"
                + "<button class=\"button button-big \" onclick=\"#\" value=\"\" name=\"\"></button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-big \" onclick=\"#\" value=\"\" name=\"\"></button>\n"
                + "<button class=\"button button-big \" onclick=\"#\" value=\"\" name=\"\"></button>\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"nextPageNoCode(this)\" value=\"\" name=\"optionsController/other\">OTHER</button>\n"
                + "</p>\n"
                + "</div>";
    }

    @RequestMapping(value = "/exteriorChoices", method = RequestMethod.GET)
    @ResponseBody
    public String exteriorChoices(HttpServletResponse response) {
        return "<div class=\"choices\">\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"loadInitialFixtureOptions()\" value=\"\" name=\"\">EXTERIOR IS HARD</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"loadInitialFixtureOptions()\" value=\"\" name=\"\">LET'S DO IT TOGETHER</button>\n"
                + "</p>\n"
                + "<p class=\"buttons-row choice-row\">\n"
                + "<button class=\"button button-raised button-big color-custom\" onclick=\"loadInitialFixtureOptions()\" value=\"\" name=\"\">WHEN YOU BUY</button>\n"
                + "</p>\n"
                + "</div>";
    }
}
