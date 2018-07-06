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
public class AutorService {

    ServiceConnection connection = new ServiceConnection();
    Connection connect = connection.getConnect();

    String addAutorSQL = "INCERT INTO \"booklist\".autor(name, lastname) values(?,?)";

    String selectAutorSQL = "SELECT id, name,lastname FROM \"booklist\".books where \"lastname\" = ?";
    String selectAutorOfBooksSQL = "SELECT b.name as bookname a.name as autorname"
            + " FROM \"booklist\".autor_Of_books as ab"
            + "inner join \"booklist\".book as b on b.id = ab.book_id "
            + "inner join \"booklist\".autor as a on a.id = ab.autor_id"
            + "where b.id = ?"
            + "order by ab.book_id";

    public void addToAutor(List<DomainBook> booksForAdd) throws SQLException {
        PreparedStatement addAutorStatment = connect.prepareStatement(addAutorSQL);
        for (DomainBook b : booksForAdd) {
            List<DomainAutor> autors = b.getAutors();
            for (DomainAutor a : autors) {
                addAutorStatment.setString(1, a.getName());
                addAutorStatment.setString(2, a.getLastName());
                addAutorStatment.execute();
            }
        }
    }

    public List<DomainAutor> selectAutor(String autorLastName) throws SQLException {
        ResultSet result = getResult(selectAutorSQL, autorLastName);
        List<DomainAutor> autorList = new ArrayList<>();
        boolean hasNext = result.next();
        if (hasNext) {
            autorList.add(getAutor(result));
        }
        return autorList;

    }

    public List<DomainAutor> selectFromBook(String BookName) throws SQLException {
        ResultSet result = getResult(selectAutorOfBooksSQL, BookName);
        List<DomainAutor> autorFromBook = new ArrayList<>();
        boolean hasNext = result.next();
        if (hasNext) {
            autorFromBook.add(getAutor(result));
        }

        return autorFromBook;

    }

    public ResultSet getResult(String path, Object object) throws SQLException {
        PreparedStatement getResultStatement = connect.prepareStatement(path);

        if (object instanceof String) {
            getResultStatement.setString(1, (String) object);
        } else if (object instanceof Integer) {
            getResultStatement.setInt(1, (Integer) object);
        }
        ResultSet result = getResultStatement.executeQuery();

        return result;

    }

    public DomainAutor getAutor(ResultSet r) throws SQLException {
        return new DomainAutor(r.getString(1), r.getString(2));
    }
}


