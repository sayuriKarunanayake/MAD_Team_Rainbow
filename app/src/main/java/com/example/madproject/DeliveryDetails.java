package com.example.madproject;

public class DeliveryDetails {
        //declare properties
    private String no;
    private String bName;
    private String street;
    private String district;

    public DeliveryDetails() {
        //default constructor
    }

    public String getNo() {

        return no;
    }

    public void setNo(String no) {

        this.no = no;
    }

    public String getbName() {

        return bName;
    }

    public void setbName(String bName) {

        this.bName = bName;
    }

    public String getStreet() {

        return street;
    }

    public void setStreet(String street) {

        this.street = street;
    }


    public String getDistrict() {

        return district;
    }

    public void setDistrict(String district) {

        this.district = district;
    }
}
