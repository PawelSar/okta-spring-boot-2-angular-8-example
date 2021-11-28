package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ShoppingList {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shoppingList;

    public String getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(String shoppingList) {
        this.shoppingList = shoppingList;
    }


    public ShoppingList() {
    }
}