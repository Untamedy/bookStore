/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noname.bookstore.domains;

import java.util.List;

/**
 *
 * @author YBolshakova
 */
 public class DomainDocument {    
     
     int id;
     List<DomainBook> bookList;    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<DomainBook> getBookList() {
        return bookList;
    }

    public void setBookList(List<DomainBook> bookList) {
        this.bookList = bookList;
    }
     
     
    
}
