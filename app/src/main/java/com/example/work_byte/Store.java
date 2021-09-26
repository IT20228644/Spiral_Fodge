package com.example.work_byte;

public class Store {

    private int id;
    private String itemname;
    private String discription;
    private byte[] image;
    private String phone;
    private String price;


    public Store(String itemname, String discription, byte[] image, int id, String phone, String price) {
        this.itemname = itemname;
        this.discription = discription;
        this.image = image;
        this.id = id;
        this.phone=phone;
        this.price=price;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemname() { return itemname; }

    public void setItemname(String itemname) { this.itemname = itemname; }

    public String getDiscription() { return discription; }

    public void setDiscription(String discription) { this.discription = discription; }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price; }

}
