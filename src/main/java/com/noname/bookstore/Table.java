/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noname.bookstore;

import com.noname.bookstore.services.ServiceConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author YBolshakova
 */
public class Table {
    
    

    ServiceConnection connection;
    
    public Table(ServiceConnection connection){
        this.connection = connection;
    }
    

    public void createTable(String SQLCreateTable) throws SQLException {
        Connection connect = connection.getConnect();
        PreparedStatement create = connect.prepareStatement(SQLCreateTable);
        create.execute();
    }

}
