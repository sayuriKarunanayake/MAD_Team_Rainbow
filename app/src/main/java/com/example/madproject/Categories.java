package com.example.mynewapp;

public class Categories {

    String catename;

    Categories(){
    }

    public Categories(String catename) {
        this.catename = catename;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename;
    }
}
