package main.java.dto;

import java.math.BigDecimal;

public class Item {
    private String id;
    private String name;
    private BigDecimal cost;
    private int stock;

    public Item(String id){
        this.id = id;
    }


    public String getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int noOfItems) {
        this.stock = noOfItems;
    }
}
