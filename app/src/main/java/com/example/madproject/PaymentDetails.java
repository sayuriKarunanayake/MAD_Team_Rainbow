package com.example.madproject;


public class PaymentDetails {
    private String CardType;
    private String Nic;
    private String CardNo;
    private String CnvNo;


    public PaymentDetails() {
    }

    public String getCardNo() {
        return CardNo;
    }

    public void setCardNo(String cardNo) {
        CardNo = cardNo;
    }

    public String getNic() {
        return Nic;
    }

    public void setNic(String nic) {
        Nic = nic;
    }

    public String getCardType() {
        return CardType;
    }

    public void setCardType(String cardType) {
        CardType = cardType;
    }

    public String getCnvNo() {
        return CnvNo;
    }

    public void setCnvNo(String cnvNo) {
        CnvNo = cnvNo;
    }
}
