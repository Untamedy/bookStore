/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noname.bookstore.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author YBolshakova
 */
public class GeneratorsService {
    ServiceConnection connect;
    Connection connection;
    
    public GeneratorsService(ServiceConnection connect) {
        this.connect = connect;
        this.connection = connection;
        connection = connect.getConnect();
    }

    String selectBookIDSQL = "SELECT gen_id FROM \"booklist\".generators where gen_name=?";
    String updateBookIDSQL = "UPDATE \"booklist\".generators SET gen_id = gen_id+1 where gen_name =?;";
    

    
  
    
    String columName;
   

    public int getGeneratorID(String name) throws SQLException {
        columName = name + "_id";
        connection = connect.getConnect();
        PreparedStatement generatorsBooks = connection.prepareStatement(selectBookIDSQL);
        generatorsBooks.setString(1, columName);
        ResultSet result = generatorsBooks.executeQuery();
        boolean hasnext = result.next();
        if (hasnext) {
            return result.getInt(1);
        }
        return 0;
    }
    

    public void updateGeneratorID(String name) throws SQLException {
        columName = name + "_id";
        PreparedStatement generatorsUpBook = connection.prepareStatement(updateBookIDSQL);
        generatorsUpBook.setString(1, columName);
        generatorsUpBook.execute();
        
    }

   
}
