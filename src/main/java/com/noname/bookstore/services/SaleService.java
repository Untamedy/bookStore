/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noname.bookstore.services;

import com.noname.bookstore.domains.DomainAutor;
import com.noname.bookstore.domains.DomainBook;
import java.io.IOException;
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
public class SaleService {
    
    DocumentWriter writeCheck = new DocumentWriter();

    ServiceConnection connection = new ServiceConnection();
    Connection connect = connection.getConnect();

    String updateAfterSaleSQL = "Update \"booklist\".books SET quantity = quantity -? where articul = ?";

    List<DomainBook> saleList = new ArrayList<>();

    public void sale(List<DomainBook> saleList) throws SQLException, IOException {
        PreparedStatement saleStatement = connect.prepareStatement(updateAfterSaleSQL);
        for(DomainBook db:saleList){
            int articul = db.getArticul();
            int bookQantity = db.getQuantity();
        saleStatement.setInt(1, bookQantity);
        saleStatement.setInt(2, articul);
        ResultSet result = saleStatement.executeQuery();    
        }
        
        writeCheck.writeChecks(saleList);

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

    public DomainBook getDomainBook(ResultSet r) throws SQLException {
        return new DomainBook(r.getInt(1), r.getString(2), r.getString(3), r.getDouble(4), r.getInt(5), r.getInt(6));
    }
}
