package com.example.foodhubpartner;

public class PartnerRegister {
    public String partner_name,partner_mobileno, partner_email, partner_address, partner_password,partnerProfile;
    int shop_count;
    public PartnerRegister(){

    }

    //creating partner account with profile pic
    PartnerRegister(String partner_name, String partner_mobileno, String partner_email, String partner_address, String partnerProfile){
        this.partner_name = partner_name;
        this.partner_mobileno = partner_mobileno;
        this.partner_email = partner_email;
        this.partner_address = partner_address;
        this.partnerProfile = partnerProfile;
        this.shop_count=0;

    }

    //creating partner account without profile pic
    PartnerRegister(String partner_name, String partner_mobileno, String partner_email, String partner_address){
        this.partner_name = partner_name;
        this.partner_mobileno = partner_mobileno;
        this.partner_email = partner_email;
        this.partner_address = partner_address;
        this.shop_count=0;
    }

    public String getPartner_name() {
        return partner_name;
    }

    public void setPartner_name(String partner_name) {
        this.partner_name = partner_name;
    }

    public String getPartner_mobileno() {
        return partner_mobileno;
    }

    public void setPartner_mobileno(String partner_mobileno) {
        this.partner_mobileno = partner_mobileno;
    }

    public String getPartner_email() {
        return partner_email;
    }

    public void setPartner_email(String partner_email) {
        this.partner_email = partner_email;
    }

    public String getPartner_address() {
        return partner_address;
    }

    public void setPartner_address(String partner_address) {
        this.partner_address = partner_address;
    }

    public String getPartner_password() {
        return partner_password;
    }

    public void setPartner_password(String partner_password) {
        this.partner_password = partner_password;
    }

    public String getPartnerProfile() {
        return partnerProfile;
    }

    public int getShop_count() {
        return shop_count;
    }

    public void setShop_count(int shop_count) {
        this.shop_count = shop_count;
    }

    public void setPartnerProfile(String partnerProfile) {
        this.partnerProfile = partnerProfile;
    }

}
