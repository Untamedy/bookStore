/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noname.bookstore.httpServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;

/**
 *
 * @author YBolshakova
 */
public class InfoHandler implements HttpHandler {

    Logger logger = Logger.getLogger(InfoHandler.class.getName());

    public void handle(HttpExchange t) throws IOException {
        logger.info("Processing request");
        String response = "It wokrs";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }
}
