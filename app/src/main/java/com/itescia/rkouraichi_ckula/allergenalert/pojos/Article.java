package com.itescia.rkouraichi_ckula.allergenalert.pojos;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Article {
    private double price = 0.0;
    private String name = "";
    private String eanCode = "";
    private String description = "";
    private Bitmap image;
    private String moreInfoLink = "";

    public ArrayList<String> allergies = new ArrayList<>();

    public Article(String name, double price) {
        this.name = name;
        this.price = price;
    }


    public Article(String name, String description, String eanCode, double price) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.eanCode = eanCode;
    }

    public Article(String name, String description, String eanCode, double price, String link) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.eanCode = eanCode;
        this.moreInfoLink = link;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEanCode() {
        return eanCode;
    }

    public void setEanCode(String eanCode) {
        this.eanCode = eanCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public ArrayList<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(ArrayList<String> allergies) {
        this.allergies = allergies;
    }

    public String getMoreInfoLink() {
        return moreInfoLink;
    }

    public void setMoreInfoLink(String moreInfoLink) {
        this.moreInfoLink = moreInfoLink;
    }

    @Override
    public String toString() {
        String s = "";

        s += this.name + "\n";
        s += this.price + "€\n";
        s += this.description + "\n";
        s += this.eanCode;
        return s;
    }
}