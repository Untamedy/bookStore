/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noname.bookstore.domains;

import java.util.List;

/**
 *
 * @author YBolshakova
 */
public class DomainBook {    
   
    int id;
    String name;  
    String autor;
    String genre;
    double price;
    boolean inStoke;
    int quantity;
    
    List<String> autors;

    public DomainBook() {

    }

    public DomainBook(String name, List<String> autors, String genre, double price, boolean inStoke, int quatity) {        
        this();
        this.autors = autors;
        this.genre = genre;
        this.price = price;
        this.inStoke = inStoke;
        this.quantity = quatity;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAutor() {
        return autors;
    }  

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isInStoke() {
        return inStoke;
    }

    public void setInStoke(boolean inStoke) {
        this.inStoke = inStoke;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
