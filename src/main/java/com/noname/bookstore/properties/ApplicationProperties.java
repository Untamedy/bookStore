/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noname.bookstore.properties;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 *
 * @author YBolshakova
 */
public class ApplicationProperties {

    public Properties properties = new Properties();
    

    public void readProperties(String path) throws FileNotFoundException, IOException {
        Logger logger = Logger.getLogger(ApplicationProperties.class.getName());
        try {
            FileReader reader = new FileReader(path);
            properties.load(reader);

        } catch (FileNotFoundException e) {
            logger.severe(e.getMessage());
        } catch (IOException ex) {
            logger.severe(ex.getMessage());
        }
    }

    public Properties getProperties() {
        return properties;

    }

}
