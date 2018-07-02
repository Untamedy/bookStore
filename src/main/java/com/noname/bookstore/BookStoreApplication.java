/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noname.bookstore;


import com.noname.bookstore.domains.DomainAutor;
import com.noname.bookstore.domains.DomainBook;
import com.noname.bookstore.properties.ApplicationProperties;
import com.noname.bookstore.services.ServiceConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author YBolshakova
 */
public class BookStoreApplication {
    public static void main(String[] args) throws IOException, SQLException {
        Logger logger = Logger.getLogger(BookStoreApplication.class.getName());
        ServiceConnection connect = new ServiceConnection();
        
        System.out.println("Start creating dataBase connect");
        try{
        ApplicationProperties prop = new ApplicationProperties();       
        prop.readProperties();
        connect.setProperties(prop);
        connect.getConnect();
        System.out.println("Connect successful");
        }
        catch(IOException e){
            System.out.println("Connection wasn't creating ");
            logger.severe(e.getMessage());
            
        }
        
        DomainAutor autor = new DomainAutor("Viktor ", "Pavlov");
        DomainBook book = new DomainBook("Fairy tales", autor.getFullName(), "fairy tale", 25, true, 1);
        System.out.println(book.getAutor());
        
        
        /*
        String sqlCreatingTable = "create table \"booklist\".test (id int, autor_id int, book_id int, foreign key (autor_id) references \"booklist\".autor (id), foreign key (book_id) references \"booklist\".books (id))";
        Table t = new Table(connect);
        try{
        t.createTable(sqlCreatingTable);
            System.out.println("Table was created");
        }
        catch(SQLException e){
            logger.severe("Table wasn't created" + e.getMessage());
        }
        */
        
    }
}
