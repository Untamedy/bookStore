/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noname.bookstore.services;

import com.noname.bookstore.domains.DomainBook;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;

/**
 *
 * @author YBolshakova
 */
public class DocumentReader {

    public static final Logger LOGGER = Logger.getLogger(DocumentReader.class.getName());

    List<DomainBook> bookList = new ArrayList<>();
    List<String> autors;

    public List<DomainBook> readFile(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String tmp = reader.readLine();
            while (tmp != null) {
                String[] data = tmp.split(",");                

                for (int i = 0; i < data.length;i+=7) {
                    autors = new ArrayList<>();
                    String[] autor = data[1].split("/");                    
                    for (int j = 0; j < autor.length; j++) {                        
                        autors.add(autor[j]);
                    }
                    double price = Double.valueOf(data[3]);
                    boolean inStoke = Boolean.valueOf(data[4]);
                    int quantity = Integer.valueOf(data[5]);
                    int articul = Integer.valueOf(data[6]);
                    bookList.add(new DomainBook(data[0], autors, data[2], price, inStoke, quantity, articul));
                }
                tmp = reader.readLine();
            }
        } catch (FileNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        return bookList;
    }
}
