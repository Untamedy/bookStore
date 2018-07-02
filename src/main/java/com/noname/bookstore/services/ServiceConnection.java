/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noname.bookstore.services;

import com.noname.bookstore.properties.ApplicationProperties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author YBolshakova
 */
public class ServiceConnection {
    static final Logger LOGGER = Logger.getLogger(ServiceConnection.class.getName());
    private Connection connection;
    
    
    public void setProperties(ApplicationProperties properties){
        
        String dbDriver = properties.getProperties().getProperty("db.driver");
        String dbUrl = properties.getProperties().getProperty("db.url");
        String dbUser = properties.getProperties().getProperty("user");
        String dbPass = properties.getProperties().getProperty("password");
        try{
        connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        }
        catch(SQLException ex){
            LOGGER.severe(ex.getMessage());
            
        }       
    
}
    public Connection getConnect(){
        return connection;
    }
    
    
    
    
    
}
