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

    private int id;
    private String name;
    private List<DomainAutor> autor;
    private String genre;
    private double price;
    private int quantity;
    private int articul;
    

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

    public void setAutor(List<DomainAutor> autor) {
        this.autor = autor;
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
    
    public String getBookinfo(DomainBook book){
        String info = book.getArticul() + " " + book.getName()+ " " + book.getGenre()+ " " + book.getPrice() + " " ;
        return info;
    }

}
