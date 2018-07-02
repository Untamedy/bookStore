/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noname.bookstore.domains;

/**
 *
 * @author YBolshakova
 */
public class DomainAutor {
    int id;
    String name;
    String lastName;
  
    
    public DomainAutor(){    
}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    
    public DomainAutor(String name, String lastName){
        this();      
        this.name=name;
        this.lastName=lastName;           
            }
    
    public String getFullName(){
        String fullName = name + " " + lastName;
        return fullName;
    }
    
    
}
