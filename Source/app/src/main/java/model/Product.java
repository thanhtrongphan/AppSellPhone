package model;

public class Product {
    private int id;
    private int categoryId;
    private String name;
    private String image;
    private String price;
    private String detail;

    public Product(int id, int categoryId, String name, String image, String price, String detail) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.image = image;
        this.price = price;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public String getDetail() {
        return detail;
    }
}
