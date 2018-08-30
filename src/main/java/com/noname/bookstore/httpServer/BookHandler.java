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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YBolshakova
 */
public class BookHandler implements HttpHandler {

    private BookService bookService;
    Logger logger = Logger.getLogger(BookHandler.class.getName());
    private JsonDecoder jsonUncoder;

    public BookHandler(BookService bookService) {
        this.jsonUncoder = new JsonDecoder();
        this.bookService = bookService;
    }

    public void handle(HttpExchange t) throws IOException {
        try {

            if (t.getRequestMethod().equalsIgnoreCase("POST")) {
                InputStream in = t.getRequestBody();
                HashMap<String, String> searchParams = jsonUncoder.convertToMap(in);
                for (Entry<String, String> entry : searchParams.entrySet()) {
                    if (entry.getKey().equalsIgnoreCase("bookname") && (entry.getValue() != null)) {
                        String bookName = entry.getValue();
                        try {
                            List<DomainBook> bookByName = bookService.selectByName(bookName);
                            String res = "";
                            for (DomainBook b : bookByName) {
                                res = b.toString() + "\n";
                            }
                            t.sendResponseHeaders(200, res.length());
                            OutputStream output = t.getResponseBody();
                            output.write(res.getBytes());
                            output.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(BookHandler.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else if (entry.getKey().equalsIgnoreCase("articul") && (entry.getValue() != null)) {
                        int bookArticul = Integer.parseInt(entry.getValue());
                        try {
                            DomainBook bookByArticul = bookService.selectByArticul(bookArticul);
                            t.sendResponseHeaders(200, bookArticul);
                            OutputStream o = t.getResponseBody();
                            o.write(bookByArticul.toString().getBytes());
                            o.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(BookHandler.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else if (entry.getKey().equalsIgnoreCase("autor") && (entry.getValue() != null)) {
                        String[] autorsArray = entry.getValue().split(",");
                        String autorname = autorsArray[0];

                        try {
                            List<DomainBook> bookByAutors = bookService.selectFromAutor(autorname);
                            String booksByThisAutor = "";
                            for (DomainBook b : bookByAutors) {
                                booksByThisAutor = booksByThisAutor + b + "\n";
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(BookHandler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (entry.getKey().equalsIgnoreCase("inStore") && (entry.getValue() != null)) {
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
                        }

                    }
                }
            } else {
                logger.info("Processing request");
                String response = "It not wokrs";
                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
                System.out.println("something wrong");
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "errror in handling", e);
        }
    }
}
