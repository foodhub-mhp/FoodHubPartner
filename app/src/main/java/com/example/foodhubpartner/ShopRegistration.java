package com.example.foodhubpartner;

public class ShopRegistration {
    public String from_time, to_time,shop_name,shop_address,shop_contact_no,shop_email_id,shop_Upi_Id,shop_Gst_no,shopProfilePic;
    public ShopRegistration(){

    }

    //registering shop without profile pic
    public ShopRegistration(String from_time, String to_time, String shop_name, String shop_address, String shop_contact_no, String shop_email_id, String shop_Upi_Id, String shop_Gst_no) {
        this.from_time = from_time;
        this.to_time = to_time;
        this.shop_name = shop_name;
        this.shop_address = shop_address;
        this.shop_contact_no = shop_contact_no;
        this.shop_email_id = shop_email_id;
        this.shop_Upi_Id = shop_Upi_Id;
        this.shop_Gst_no = shop_Gst_no;
    }

    //registering shop with profile pic
    public ShopRegistration(String from_time, String to_time, String shop_name, String shop_address, String shop_contact_no, String shop_email_id, String shop_Upi_Id, String shop_Gst_no, String shopProfilePic) {
        this.from_time = from_time;
        this.to_time = to_time;
        this.shop_name = shop_name;
        this.shop_address = shop_address;
        this.shop_contact_no = shop_contact_no;
        this.shop_email_id = shop_email_id;
        this.shop_Upi_Id = shop_Upi_Id;
        this.shop_Gst_no = shop_Gst_no;
        this.shopProfilePic = shopProfilePic;
    }

    public String getFrom_time() {
        return from_time;
    }

    public void setFrom_time(String from_time) {
        this.from_time = from_time;
    }

    public String getTo_time() {
        return to_time;
    }

    public void setTo_time(String to_time) {
        this.to_time = to_time;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public String getShop_contact_no() {
        return shop_contact_no;
    }

    public void setShop_contact_no(String shop_contact_no) {
        this.shop_contact_no = shop_contact_no;
    }

    public String getShop_email_id() {
        return shop_email_id;
    }

    public void setShop_email_id(String shop_email_id) {
        this.shop_email_id = shop_email_id;
    }

    public String getShop_Upi_Id() {
        return shop_Upi_Id;
    }

    public void setShop_Upi_Id(String shop_Upi_Id) {
        this.shop_Upi_Id = shop_Upi_Id;
    }

    public String getShop_Gst_no() {
        return shop_Gst_no;
    }

    public void setShop_Gst_no(String shop_Gst_no) {
        this.shop_Gst_no = shop_Gst_no;
    }

    public String getShopProfilePic() {
        return shopProfilePic;
    }

    public void setShopProfilePic(String shopProfilePic) {
        this.shopProfilePic = shopProfilePic;
    }
}
