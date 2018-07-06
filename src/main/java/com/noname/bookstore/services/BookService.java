/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noname.bookstore.services;

import com.noname.bookstore.domains.DomainBook;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author YBolshakova
 */
public class BookService {

    ServiceConnection connection = new ServiceConnection();
    Connection connect = connection.getConnect();

    String addBooksSQL = "INCERT INTO \"booklist\".books (name, genre, price,instoke,quantity,articul values(?,?,?,?,?)";
    String selectBooksSQL = "SELECT id,name, genre, price,instoke,quantity FROM \"booklist\".books where \"articul\" = ?";
    String selectByName = "SELECT id,name, genre, price,quantity,articul FROM \"booklist\".books where \"name\" = ?";
    String selectByInStore = "Select * from \"booklist\".books where quantity>0";
    String selectBookFromAutorSQL = "SELECT b.name as bookname a.name as autorname"
            + " FROM \"booklist\".autor_Of_books as ab"
            + "inner join \"booklist\".book as b on b.id = ab.book_id "
            + "inner join \"booklist\".autor as a on a.id = ab.autor_id"
            + "where a.id = ?"
            + "order by ab.autor_id";
    String updateBooksSQL = "Update \"booklist\".books SET quantity = quantity +?, instore = true where id = ?";
    
    
     public void addToBooks(List<DomainBook> books) throws SQLException {
         
        PreparedStatement addBooksStatment = connect.prepareStatement(addBooksSQL);
        for(DomainBook book:books){
        addBooksStatment.setString(1, book.getName());
        addBooksStatment.setString(2, book.getGenre());
        addBooksStatment.setDouble(3, book.getPrice());
        addBooksStatment.setInt(4, book.getQuantity());
        addBooksStatment.setInt(5, book.getArticul());
        addBooksStatment.execute();
        }
    }
     
     public List<DomainBook> selectFromAurot(String autorLastname) throws SQLException{
         ResultSet result = getResult(selectBookFromAutorSQL, autorLastname);
         List<DomainBook> bookFromAutor = new ArrayList<>();
         boolean hasNext = result.next();
         if(hasNext){
             bookFromAutor.add(getDomainBook(result));
                      }
         
         return bookFromAutor;        
         
     }
     
     
     public DomainBook selectByArticul(int articul) throws SQLException {
        ResultSet result = getResult(selectBooksSQL, articul);
        boolean hasNext = result.next();
        if (hasNext) {
            return getDomainBook(result);
        }
        return null;

    }
     
     public List<DomainBook> selectByName(String bookName) throws SQLException {
        ResultSet result = getResult(selectByName,bookName);        
        List<DomainBook> bookNameList = new ArrayList<>();
        boolean hasNext = result.next();
        if(hasNext){
            bookNameList.add(getDomainBook(result));            
        }
        return bookNameList;

    }
     
     public List<DomainBook> selectByInStore() throws SQLException {
         PreparedStatement selectByInStoreStatement =  connect.prepareStatement(selectByInStore);
         List<DomainBook> inStoreList = new ArrayList<>();
         ResultSet result = selectByInStoreStatement.executeQuery();
         boolean hasNext = result.next();
         if(hasNext){
             inStoreList.add(getDomainBook(result));
         }
         
        return inStoreList;

    }
     
     public void update(DomainBook book, ResultSet result) throws SQLException {
        PreparedStatement updateBookStatement = connect.prepareStatement(updateBooksSQL);
        if (null != selectByArticul(book.getArticul())) {
            updateBookStatement.setInt(1, book.getQuantity());
        }
    }
     
     
     public ResultSet getResult(String path, Object object) throws SQLException {
        PreparedStatement getResultStatement = connect.prepareStatement(path);
               
        if(object instanceof String){
           getResultStatement.setString(1,(String)object);           
                    }
        else if(object instanceof Integer){
            getResultStatement.setInt(1,(Integer)object);            
        }
        ResultSet result = getResultStatement.executeQuery();
        
        return result;

    }

    public DomainBook getDomainBook(ResultSet r) throws SQLException {
        return new DomainBook(r.getInt(1), r.getString(2), r.getString(3), r.getDouble(4), r.getInt(5), r.getInt(6));
    }

}
