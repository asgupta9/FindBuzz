package com.example.findbuzz.findbuzz;

/**
 * Created by akash on 16/1/18.
 */

public class RequestResponsesCardLayout {


    private String username,remark,date;
    private int price;
    private  int id;

    public RequestResponsesCardLayout(String username, String remark, String date, int price, int id) {
        this.username = username;
        this.remark = remark;
        this.date = date;
        this.price = price;
        this.id = id;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
