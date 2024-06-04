package model;

public class Order {
    int id;
    String total;
    String address;
    String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Order(int id, String total, String address, String phone) {
        this.id = id;
        this.total = total;
        this.address = address;
        this.phone = phone;
    }
}
