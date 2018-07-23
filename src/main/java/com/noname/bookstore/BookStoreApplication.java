/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noname.bookstore;

import com.noname.bookstore.Business.SaleMan;
import com.noname.bookstore.domains.DomainBook;
import com.noname.bookstore.properties.ApplicationProperties;
import com.noname.bookstore.services.AutorService;
import com.noname.bookstore.services.BookService;

import com.noname.bookstore.services.GeneratorsService;
import com.noname.bookstore.services.SaleService;
import com.noname.bookstore.services.ServiceConnection;
import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author YBolshakova
 */
public class BookStoreApplication {

    public static void main(String[] args) throws IOException, SQLException {
        Logger logger = Logger.getLogger(BookStoreApplication.class.getName());
        ServiceConnection connect = new ServiceConnection();
        List<DomainBook> saleBookList = new ArrayList<>();
        GeneratorsService generatorsService = new GeneratorsService(connect);
        BookService bookService = new BookService(generatorsService, connect);
        AutorService autorService = new AutorService(generatorsService, connect);
        SaleService saleService = new SaleService(connect);

        String path = "D:/tmp/inputListOfBooks.txt";

        String pathProp = "D:/tmp/conectPropBooks.properties";
        System.out.println("Start creating dataBase connect");
        try {
            ApplicationProperties prop = new ApplicationProperties();
            prop.readProperties(pathProp);
            connect.setProperties(prop);
            connect.getConnect();
            System.out.println("Connect successful");
        } catch (IOException e) {
            System.out.println("Connection wasn't creating ");
            logger.severe(e.getMessage());

        }

        System.out.println("Start insert books");
        SaleMan man = new SaleMan(saleService, bookService, autorService);
        man.addBooks(path);
        System.out.println("Books insert successful");
        List<DomainBook> booklistFromautor = man.selectBookByAutor("V.K. Petrov");
        for (DomainBook b : booklistFromautor) {
            System.out.println(b.getName() + "\n");
        }
        List<DomainBook> bookByName = man.selectBooksByName("Fairy tales");
        for (DomainBook b : bookByName) {
            System.out.println(b.getId() + " " + b.getName() + " " + b.getPrice() + " " + "\n");
        }
        List<DomainBook> inStore = man.selectbookByInStore();
        for (DomainBook b : inStore) {
            System.out.println(b.getId() + " " + b.getName() + " " + b.getPrice() + " " + b.getQuantity() + "\n");
        }
        man.saleBooks();

    }
}

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
