/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noname.bookstore.Domains;

/**
 *
 * @author YBolshakova
 */
public class DomeinAutor {
    int id;
    String name;
    String lastName;
    int rating;
    
    public DomeinAutor(){    
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

    public int getRating() {
        return rating;
    }
    public DomeinAutor(String name, String lastName, int rating){
        this();      
        this.name=name;
        this.lastName=lastName;
        this.rating=rating;     
            }
    
    
}
