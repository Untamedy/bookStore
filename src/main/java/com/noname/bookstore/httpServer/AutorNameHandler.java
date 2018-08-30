/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noname.bookstore.httpServer;

import com.noname.bookstore.domains.DomainBook;
import com.noname.bookstore.services.BookService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YBolshakova
 */
public class AutorNameHandler implements HttpHandler {

    BookService bookService;

    Logger logger = Logger.getLogger(AutorNameHandler.class.getName());

    public AutorNameHandler(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void handle(HttpExchange t) throws IOException {
        String response = "";
        String autorName = "";
        try {
            if(t.getRequestMethod().equalsIgnoreCase("post")){
                t.getRequestBody();
            InputStream autor = t.getRequestBody();
            autor.read();
            autorName = autorName.substring(1);
            autorName = autorName.replace('%20'," ");
            System.out.println(autorName);
                
            }
            //URI uri = t.getRequestURI();
            //String autorName = uri.toString();
            //autorName = autorName.substring(1);
            //autorName = autorName.replace('%20'," ");
            //System.out.println(autorName);
            List<DomainBook> bookFromAutor = bookService.selectFromAutor(autorName);
            for (DomainBook b : bookFromAutor) {
                response = response + "\n" + b;
                t.sendResponseHeaders(200, response.length());
                OutputStream out = t.getResponseBody();
                out.write(response.getBytes());
                out.close();
            }

            //List<DomainBook> bookFromAutor = bookService.selectFromAutor(response);
        } catch (SQLException ex) {
            logger.severe("Autor name don't found or don't right");
        }

    }

}

/*public void handle(HttpExchange t) throws IOException {
        if (t.getRequestMethod().equals("POST")) {
            InputStream in = t.getRequestBody();
            logger.info(String.valueOf(in.read()));
        }
        try {
            logger.info("Processing request");
            List<DomainBook> bookList = bookService.selectByInStore();
            String response = "";
            for (DomainBook b : bookList) {
                response = response + " " + b;
            }
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } catch (SQLException ex) {
            logger.severe("Error getting list of books");
 */
