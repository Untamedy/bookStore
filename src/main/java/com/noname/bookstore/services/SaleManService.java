/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noname.bookstore.services;

import com.noname.bookstore.domains.DomainAutor;
import com.noname.bookstore.domains.DomainBook;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author YBolshakova
 */
public class SaleManService {
    ServiceConnection connection  = new ServiceConnection();
    Connection connect = connection.getConnect();
    
    String addBooksSQL = "INCERT INTO \"booklist\".books (name, genre, price,instoke,quantity,articul values(?,?,?,?,?)";
    String addAutorSQL = "INCERT INTO \"booklist\".autor(name, lastname) values(?,?)";
    String addAutorOfBooks = "INCERT INTO \"booklist\".autor_of_books (id,autor_id, book_id) values (?,?,?)";
    String selectBooksSQL = "SELECT id,name, genre, price,instoke,quantity FROM \"booklist\".books where \"articul\" = ?";
    String selectAutorSQL = "SELECT id, name,lastname FROM \"booklist\".books where \"lastname\" = ?";
    String selectAutorOfBooksSQL = "SELECT autor_id FROM \"booklist\".autor_Of_books where \"book_id\"=?";
    String selectBookFromAutorSQL = "SELECT book_id FROM \"booklist\".autor_Of_books where \"autor_id\"=?";
    String updateBooksSQL = "Update \"booklist\".books SET quantity = quantity +?, instore = true where id = ?";
    String updateAfterSaleSQL = "Update \"booklist\".books SET quantity = quantity -?, instore = false where id = ?";
    
    
       
        
    
    public void addToBooks(DomainBook book) throws SQLException{
    PreparedStatement addBooksStatment = connect.prepareStatement(addBooksSQL);
    addBooksStatment.setString(1,book.getName());
    addBooksStatment.setString(2, book.getGenre());
    addBooksStatment.setDouble(3, book.getPrice());
    addBooksStatment.setBoolean(4, book.getisInStoke());
    addBooksStatment.setInt(5, book.getQuantity());
    addBooksStatment.setInt(6, book.getArticul());    
    addBooksStatment.execute();       
    }
    
    public void addToAutor(DomainAutor autor) throws SQLException{
     PreparedStatement addAutorStatment = connect.prepareStatement(addAutorSQL);
     addAutorStatment.setString(1,autor.getName() );
     addAutorStatment.setString(2, autor.getLastName()); 
     addAutorStatment.execute();
    }
    
    public void addAutorOfBook() throws SQLException{
    PreparedStatement addAutorOfBooksStatment = connect.prepareStatement(addAutorOfBooks);
    
        
    }
    
    public void select() throws SQLException{
    PreparedStatement selectBooksStatment = connect.prepareStatement(selectBooksSQL); 
        
    }
    
    public void selectByautor(){
        
    }
    
    public void selectByName(){
        
    }
    
    public void selectByInStore(){
        
    }
    
    public void update(){
        
    }
    
    public void sale(){
        
    }
}
