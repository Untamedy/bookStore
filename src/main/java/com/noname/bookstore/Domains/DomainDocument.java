/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noname.bookstore.Domains;

import java.util.List;

/**
 *
 * @author YBolshakova
 */
 public class DomainDocument {    
     
     int id;
     List<AppBooksStore> bookList;    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<AppBooksStore> getBookList() {
        return bookList;
    }

    public void setBookList(List<AppBooksStore> bookList) {
        this.bookList = bookList;
    }
     
     
    
}
