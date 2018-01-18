package com.example.findbuzz.findbuzz;

/**
 * Created by akash on 16/1/18.
 */

public class RequestResponsesCardLayout {


    private String username,user_email,remark,date;
    private String price;
    private String id;

    public RequestResponsesCardLayout(String username, String remark, String date, String price, String id) {
        this.username = username;
        this.remark = remark;
        this.date = date;
        this.price = price;
        this.id = id;
    }
    public RequestResponsesCardLayout(String username, String remark, String date, String price, String id, String email) {
        this.username = username;
        this.remark = remark;
        this.date = date;
        this.price = price;
        this.id = id;
        this.user_email = email;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
