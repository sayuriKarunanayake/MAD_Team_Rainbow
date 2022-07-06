package com.example.mynewapp;

public class model {

    String bookname;
   String bookdes;
    String bookprice;
    String bookcategory;
    String imgurl;

    model() {

    }

    public model(String bookname, String bookdes, String bookprice, String bookcategory, String imgurl) {
        this.bookname = bookname;
        this.bookdes = bookdes;
        this.bookprice = bookprice;
        this.bookcategory = bookcategory;
        this.imgurl = imgurl;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookdes() {
        return bookdes;
    }

    public void setBookdes(String bookdes) {
        this.bookdes = bookdes;
    }

    public String getBookprice() {
        return bookprice;
    }

    public void setBookprice(String bookprice) {
        this.bookprice = bookprice;
    }

    public String getBookcategory() {
        return bookcategory;
    }

    public void setBookcategory(String bookcategory) {
        this.bookcategory = bookcategory;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}