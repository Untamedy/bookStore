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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author YBolshakova
 */
public class BookService {

    AutorService autor;

    private GeneratorsService generatorsService;
    private ServiceConnection connection;
    private Connection connect;

    public final String addBooksSQL = "INSERT INTO \"booklist\".books (id,name, genre, price,quantity,articul) values(?,?,?,?,?,?)";

    public final String selectBooksSQL = "SELECT id,name, genre, price,quantity,articul FROM \"booklist\".books where \"articul\" = ?";
    public final String selectByName = "SELECT * FROM \"booklist\".books where \"name\" = ?";
    public final String selectByInStore = "Select * from \"booklist\".books where quantity>0";
    public final String selectBookFromAutorSQL = "select b.* from \"booklist\".books b, \"booklist\".autor a, \"booklist\".autor_of_books ab "
            + "where a.\"fullname\"=? "
            + "and a.id = ab.autor_id "
            + "and b.id = ab.book_id";
    public final String updateBooksSQL = "Update \"booklist\".books SET quantity = quantity +? where id = ?";

    public BookService(GeneratorsService generatorsService, ServiceConnection connection) {
        this.generatorsService = generatorsService;
        this.connection = connection;
        connect = connection.getConnect();
    }

    public int getID() throws SQLException {
        generatorsService = new GeneratorsService(connection);
        return generatorsService.getGeneratorID("book");
    }

    public List<DomainBook> addBooks(List<DomainBook> book) throws SQLException {
        Connection connect = connection.getConnect();
        List<DomainBook> result = new ArrayList<>(book.size());
        for (DomainBook b : book) {
            if (null != selectByArticul(b.getArticul())) {
                update(b, connect);
                b.setId(selectByArticul(b.getArticul()).getId());
                result.add(b);
            } else {
                b.setId(getID());
                insertBook(b, connect);
                result.add(b);
            }
        }
        return result;
    }

    public void insertBook(DomainBook book, Connection connect) throws SQLException {
        PreparedStatement addBooksStatment = connect.prepareStatement(addBooksSQL);
        addBooksStatment.setInt(1, getID());
        addBooksStatment.setString(2, book.getName());
        addBooksStatment.setString(3, book.getGenre());
        addBooksStatment.setDouble(4, book.getPrice());
        addBooksStatment.setInt(5, book.getQuantity());
        addBooksStatment.setInt(6, book.getArticul());
        addBooksStatment.execute();
        generatorsService.updateGeneratorID("book");

    }

    public List<DomainBook> selectFromAutor(String autorfullname) throws SQLException {
        ResultSet result = getResult(selectBookFromAutorSQL, autorfullname);
        List<DomainBook> bookFromAutor = new ArrayList<>();
        boolean hasNext = result.next();
        if (hasNext) {
            bookFromAutor.add(getDomainBook(result));
            return bookFromAutor;
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
        ResultSet result = getResult(selectByName, bookName);
        List<DomainBook> bookNameList = new ArrayList<>();
        boolean hasNext = result.next();
        if (hasNext) {
            bookNameList.add(getDomainBook(result));
        }
        return bookNameList;

    }

    public List<DomainBook> selectByInStore() throws SQLException {        
        PreparedStatement selectByInStoreStatement = connect.prepareStatement(selectByInStore);
        List<DomainBook> inStoreList = new ArrayList<>();
        ResultSet result = selectByInStoreStatement.executeQuery();
        boolean hasNext = result.next();
        while (hasNext) {
            inStoreList.add(getDomainBook(result));
            hasNext = result.next();
        }

        return inStoreList;

    }

    public void update(DomainBook book, Connection connect) throws SQLException {
        PreparedStatement updateBookStatement = connect.prepareStatement(updateBooksSQL);
        updateBookStatement.setInt(1, book.getQuantity());
        updateBookStatement.setInt(2, book.getId());
        updateBookStatement.execute();

    }

    public ResultSet getResult(String path, Object object) throws SQLException {
        connect = connection.getConnect();
        PreparedStatement getResultStatement = connect.prepareStatement(path);

        if (object instanceof String) {
            getResultStatement.setString(1, (String) object);
        } else if (object instanceof Integer) {
            getResultStatement.setInt(1, (Integer) object);
        }
        ResultSet result = getResultStatement.executeQuery();

        return result;

    }

    public DomainBook getDomainBook(ResultSet r) throws SQLException {
        return new DomainBook(r.getInt(1), r.getString(2), r.getString(3), r.getDouble(4), r.getInt(5), r.getInt(6));
    }

}
