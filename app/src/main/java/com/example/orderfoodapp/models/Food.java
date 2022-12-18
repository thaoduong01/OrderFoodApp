package com.example.orderfoodapp.models;

public class Food {

    public int img;
    public int id;
    public String name;
    public String time;
    public String rating;
    public float price;
    public String caterogy;

    public Food() {

    }

    public Food(int img, int id, String name, String time, String rating, float price, String caterogy) {
        this.img = img;
        this.id = id;
        this.name = name;
        this.time = time;
        this.rating = rating;
        this.price = price;
        this.caterogy = caterogy;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCaterogy() {
        return caterogy;
    }

    public void setCaterogy(String caterogy) {
        this.caterogy = caterogy;
    }
}
