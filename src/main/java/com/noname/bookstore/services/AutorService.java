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

    GeneratorsService generatorsService;
    ServiceConnection connection;
    Connection connect;

    public AutorService(GeneratorsService generatorsService, ServiceConnection connection) {
        this.generatorsService = generatorsService;
        this.connection = connection;
        this.connect = connect;
        connect = connection.getConnect();
    }

    String addAutorSQL = "INSERT INTO \"booklist\".autor(id,name,lastname,fullname) values(?,?,?,?)";
    public final String addToAutorsOfBookSQL = "INSERT INTO \"booklist\".autor_of_books (book_id, autor_id) values(?,?)";

    String selectAutorSQL = "SELECT id,name,lastname FROM \"booklist\".autor where \"fullname\" = ?";
    String selectAutorOfBooksSQL = "SELECT a.* "
            + "FROM \"booklist\".autor_Of_books as ab "
            + "inner join \"booklist\".books as b on b.id = ab.book_id "
            + "inner join \"booklist\".autor as a on a.id = ab.autor_id "
            + "where b.id = ? "
            + "order by ab.book_id";

    public int getGenerator() throws SQLException {
        return generatorsService.getGeneratorID("autor");
    }

    public List<DomainBook> addAutors(List<DomainBook> booksForAdd) throws SQLException {
        connect = connection.getConnect();
        for (DomainBook b : booksForAdd) {
            List<DomainAutor> autors = b.getAutors();
            List<DomainAutor> result = new ArrayList<>(booksForAdd.size());
            for (DomainAutor a : autors) {
                if (null == selectAutor(a.getFullName())) {
                    a.setId(getGenerator());
                    insertAutor(a, connect);
                    result.add(a);
                } else {
                    a.setId(selectAutor(a.getFullName()).getId());
                    result.add(a);
                }
            }
            b.setAutor(result);
        }
        return booksForAdd;

    }

    public void insertToAutor_of_books(List<DomainBook> books) throws SQLException {
        connect = connection.getConnect();
        PreparedStatement addBookId = connect.prepareStatement(addToAutorsOfBookSQL);
        for (DomainBook b : books) {
            int bookId = b.getId();
            if (null != selectFromBook(bookId)) {
                List<DomainAutor> autors = b.getAutors();
                for (DomainAutor a : autors) {
                    addBookId.setInt(1, bookId);
                    addBookId.setInt(2, a.getId());
                    addBookId.execute();
                }
            }
        }
    }

    public void insertAutor(DomainAutor autor, Connection connect) throws SQLException {
        PreparedStatement addAutorStatment = connect.prepareStatement(addAutorSQL);
        addAutorStatment.setInt(1, getGenerator());
        addAutorStatment.setString(2, autor.getName());
        addAutorStatment.setString(3, autor.getLastName());
        addAutorStatment.setString(4, autor.getFullName());
        addAutorStatment.execute();
        generatorsService.updateGeneratorID("autor");
    }

    public DomainAutor selectAutor(String autorFullName) throws SQLException {
        connect = connection.getConnect();
        ResultSet result = getResult(selectAutorSQL, autorFullName);
        List<DomainAutor> autorList = new ArrayList<>();
        boolean hasNext = result.next();
        if (hasNext) {
            return getAutor(result);
        }
        return null;
    }

    public List<DomainAutor> selectFromBook(int bookId) throws SQLException {
        ResultSet result = getResult(selectAutorOfBooksSQL, bookId);
        List<DomainAutor> autorFromBook = new ArrayList<>();
        boolean hasNext = result.next();
        if (hasNext) {
            autorFromBook.add(getAutor(result));
        }

        return autorFromBook;

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

    public DomainAutor getAutor(ResultSet r) throws SQLException {
        return new DomainAutor(r.getInt(1),r.getString(2), r.getString(3));
    }
}
