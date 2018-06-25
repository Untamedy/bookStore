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
public class DomainBook {

    int id;
    String name;
    String autor;
    String genre;
    double price;
    boolean inStoke;
    int quantity;

    public DomainBook() {

    }

    public DomainBook(String name, String autor, String genre, double price, boolean inStoke, int quaytity) {
        this();
        this.autor = autor;
        this.genre = genre;
        this.price = price;
        this.inStoke = inStoke;
        this.quantity = quaytity;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
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
