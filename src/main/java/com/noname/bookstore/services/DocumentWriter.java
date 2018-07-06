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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javafx.beans.binding.Bindings;

/**
 *
 * @author YBolshakova
 */
public class DocumentWriter {    
    String fileName = "Thank you for your purchase!";
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
    
    
    public void writeChecks(List<DomainBook> saleBookList) throws IOException{
    File check = new File("D:/tmp/cheks/check_" + dateFormat.format(date) + ".txt");
    FileWriter writer = new FileWriter(check, true);    
    writer.write(fileName + "/n****" );
    for(DomainBook sale: saleBookList){        
        writer.write(sale.toString());
    }

    writer.flush();
    writer.close();
    }
    
}
