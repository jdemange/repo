/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demange.touchcount.dao;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author jocel
 */
public class UserDaoImpl implements UserDao{
    private JdbcTemplate jt;
    
    public void setJdbcTemplate(JdbcTemplate jt){
        this.jt = jt;
    }
}
