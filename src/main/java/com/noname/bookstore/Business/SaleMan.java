/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noname.bookstore.Business;

import com.noname.bookstore.domains.DomainAutor;
import com.noname.bookstore.domains.DomainBook;
import com.noname.bookstore.services.AutorService;
import com.noname.bookstore.services.BookService;
import com.noname.bookstore.services.DocumentReader;
import com.noname.bookstore.services.SaleService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author YBolshakova
 */
public class SaleMan {

    SaleService saleService;
    BookService bookService;
    AutorService autorService;

    public void addBooks(String path) throws SQLException {
        DocumentReader reader = new DocumentReader();
        List<DomainBook> booksForAdd = reader.readFile(path);
        bookService.addToBooks(booksForAdd);
        autorService.addToAutor(booksForAdd);
    }

    public DomainBook selectbooksByArticul(int articul) throws SQLException {
        return bookService.selectByArticul(articul);
    }

    public List<DomainBook> selectbookByInStore() throws SQLException {
        return bookService.selectByInStore();
    }

    public List<DomainBook> selectBookByAutor(String lastname) throws SQLException {
        return bookService.selectFromAurot(lastname);
    }

    public List<DomainBook> selectBooksByName(String name) throws SQLException {
        return bookService.selectByName(name);
    }

    public void saleBooks(List<DomainBook> books) throws SQLException, IOException {
        saleService.sale(books);
    }

}
