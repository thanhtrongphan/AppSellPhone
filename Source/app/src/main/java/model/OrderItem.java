package model;

public class OrderItem {
    String name;
    String urlImage;
    int number;
    String price;
    String total;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public OrderItem(String name, String urlImage, int number, String price, String total) {
        this.name = name;
        this.urlImage = urlImage;
        this.number = number;
        this.price = price;
        this.total = total;
    }
}
