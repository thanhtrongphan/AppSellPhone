package model;

public class Category {



    private  String id;
    private String name;
    private String image;

    public Category() {
    }
    public Category(String id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }
    public int getId() {

        return Integer.parseInt(id);
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
