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
    List<DomainAutor> autor;
    String genre;
    double price;
    int quantity;
    int articul;
    

    public DomainBook() {

    }

    public DomainBook(int id, String name, List<DomainAutor> autors, String genre, double price, int quatity, int articul) {
        this.id = id;
        this.autor = autors;
        this.name = name;
        this.genre = genre;
        this.price = price;
        this.quantity = quatity;
        this.articul = articul;
    }

    public DomainBook(String name, List<DomainAutor> autors, String genre, double price, int quatity, int articul) {
        this();
        this.autor = autors;
        this.name = name;
        this.genre = genre;
        this.price = price;
        this.quantity = quatity;
        this.articul = articul;
    }

    public DomainBook(int id, String name, String genre, double price, int quatity, int articul) {
        this();
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.price = price;
        this.quantity = quatity;
        this.articul = articul;
    }

    public List<DomainAutor> getAutors() {
        return autor;
    }

    public int getArticul() {
        return articul;
    }

    public void setArticul(int articul) {
        this.articul = articul;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
