/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demange.touchcount.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author jocel
 */
@Controller
@RequestMapping("/userController")
public class UserController {

    @RequestMapping(value = "/displayLoginPage", method = RequestMethod.GET)
    @ResponseBody
    public String displayLoginPage() {
        return "<div class=\"choices\">\n"
                + "                                    <div class=\"card container user-sign-in\">\n"
                + "                                        <div class=\"card-content\">\n"
                + "                                            <div class=\"row\">\n"
                + "                                                <div class=\"col-100 tablet-100 card-content-inner\">\n"
                + "                                                    <div class=\"list-block inset\">\n"
                + "                                                        <div class=\"item-content\">\n"
                + "                                                            <div class=\"item-inner\">\n"
                + "                                                                <div class=\"item-title floating-label\" align=\"center\">User Name </div>\n"
                + "                                                                <div class=\"item-input bg-white border-gray\">\n"
                + "                                                                    <input type=\"text\" name=\"userName\" id=\"userName\">\n"
                + "                                                                </div>\n"
                + "                                                            </div>\n"
                + "                                                        </div>\n"
                + "                                                    </div>\n"
                + "                                                </div>\n"
                + "                                                <div class=\"col-100 tablet-100 card-content-inner\">\n"
                + "                                                    <div class=\"list-block inset\">\n"
                + "                                                        <div class=\"item-content\">\n"
                + "                                                            <div class=\"item-inner\">\n"
                + "                                                                <div class=\"item-title floating-label\" align=\"center\">Password </div>\n"
                + "                                                                <div class=\"item-input bg-white border-gray\">\n"
                + "                                                                    <input type=\"text\" name=\"password\" id=\"password\">\n"
                + "                                                                </div>\n"
                + "                                                            </div>\n"
                + "                                                        </div>\n"
                + "                                                    </div>\n"
                + "                                                </div>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                        <p class=\"buttons-row\">\n"
                + "\n"
                + "                                            <button class=\"button color-custom button-big button-raised\" onclick=\"login()\">Login</button>\n"
                + "                                        </p>\n"
                + "\n"
                + "                                    </div>\n"
                + "                                </div>";
    }

}
