package org.example.drinkVendor_system;


public class Item {

    private int id;
    private String name;
    private int price;
    private String imgURL;


    public Item(int id, String name, int price, String imgURL) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgURL = imgURL;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }


    public String getImgURL() {
        return imgURL;
    }
}
