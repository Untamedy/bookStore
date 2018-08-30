/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noname.bookstore.httpServer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author YBolshakova
 */
public class JsonDecoder {

    Logger logger = Logger.getLogger(JsonDecoder.class.getName());
    ObjectMapper objectMapper = new ObjectMapper();

    public byte[] readerJson() {
        try {
            byte[] searchList = Files.readAllBytes(Paths.get("D:/tmp/json.txt"));
            return searchList;
        } catch (IOException e) {
            logger.severe("Can't read json " + e.getMessage());
            return null;
        }

    }

    public HashMap<String, String> convertToMap(InputStream stream) throws IOException {
        return objectMapper.readValue(stream, new TypeReference<HashMap<String, String>>() {
        });
    }

}
