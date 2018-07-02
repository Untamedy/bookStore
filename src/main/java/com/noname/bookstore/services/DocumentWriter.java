/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noname.bookstore.services;

import com.noname.bookstore.domains.DomainBook;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author YBolshakova
 */
public class DocumentWriter {
    List<DomainBook> saleBookList;
    String fileName;
    
    public void write() throws IOException{
    File check = new File("D:/tmp/check.txt");
    FileWriter writer = new FileWriter(check);
    writer.write(fileName);
    
    }
    
}
