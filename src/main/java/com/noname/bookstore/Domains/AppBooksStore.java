/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noname.bookstore.Domains;

import com.noname.bookstore.Properties.ApplicationProperties;
import com.noname.bookstore.Services.ServiceConnection;
import com.sun.corba.se.impl.activation.ServerMain;
import java.io.IOException;
import java.util.logging.Logger;

/**
 *
 * @author YBolshakova
 */
class AppBooksStore {

    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger(AppBooksStore.class.getName());
        
        System.out.println("Start creating dataBase connect");
        try{
        ApplicationProperties prop = new ApplicationProperties();
        ServiceConnection connect = new ServiceConnection();

        prop.readProperties();
        connect.setProperties(prop);
        connect.getConnect();
        System.out.println("Connect successful");
        }
        catch(IOException e){
            System.out.println("Connection wasn't creating ");
            logger.severe(e.getMessage());
            
        }
    }

}
