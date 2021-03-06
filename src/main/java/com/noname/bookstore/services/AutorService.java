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

    private GeneratorsService generatorsService;
    private ServiceConnection connection;
    private Connection connect;

    public AutorService(GeneratorsService generatorsService, ServiceConnection connection) {
        this.generatorsService = generatorsService;
        this.connection = connection;
        this.connect = connect;
        connect = connection.getConnect();
    }

    public final String addAutorSQL = "INSERT INTO \"booklist\".autor(id,name,lastname,fullname) values(?,?,?,?)";
    public final String addToAutorsOfBookSQL = "INSERT INTO \"booklist\".autor_of_books (book_id, autor_id) values(?,?)";

    public final String selectAutorSQL = "SELECT id,name,lastname FROM \"booklist\".autor where \"fullname\" = ?";
    public final String selectAutorOfBooksSQL = "SELECT a.* "
            + "FROM \"booklist\".autor_Of_books as ab "
            + "inner join \"booklist\".books as b on b.id = ab.book_id "
            + "inner join \"booklist\".autor as a on a.id = ab.autor_id "
            + "where b.id = ? "
            + "order by ab.book_id";

    public final String selectForAutors_of_book = "SELECT * FROM \"booklist\".autor_Of_books where book_id = ? and autor_id = ?";

    public int getGenerator() throws SQLException {
        generatorsService = new GeneratorsService(connection);
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
            List<DomainAutor> autors = b.getAutors();
            for (DomainAutor a : autors) {
                int autorId = a.getId();
                if (!selectFromBookAndAutorId(bookId, autorId)) {
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

    public boolean selectFromBookAndAutorId(int bookId, int autorId) throws SQLException {
        PreparedStatement selectBookAndAutor = connect.prepareStatement(selectForAutors_of_book);
        selectBookAndAutor.setInt(1, bookId);
        selectBookAndAutor.setInt(2, autorId);
        ResultSet result = selectBookAndAutor.executeQuery();
        List<DomainAutor> autorFromBook = new ArrayList<>();
        boolean hasNext = result.next();
        if (hasNext) {
            return hasNext;
        }
        return false;
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
        return new DomainAutor(r.getInt(1), r.getString(2), r.getString(3));
    }
}
