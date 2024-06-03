package model;

import java.io.Serializable;

public class CartItem implements Serializable {
    private int id;
    private int productId;
    private String title;
    private String picUrl;
    private int numberinCart;
    private double price;

    private double total;

    public CartItem(int id, int productId, String title, String picUrl, int numberinCart, double price, double total) {
        this.id = id;
        this.productId = productId;
        this.title = title;
        this.picUrl = picUrl;
        this.numberinCart = numberinCart;
        this.price = price;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getNumberinCart() {
        return numberinCart;
    }

    public void setNumberinCart(int numberinCart) {
        this.numberinCart = numberinCart;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
