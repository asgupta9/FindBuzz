package com.example.findbuzz.findbuzz;

import android.provider.ContactsContract;

import java.util.Date;

/**
 * Created by akash on 12/1/18.
 */

class CardLayoutRequest {

    private int id;
    private String requestDate;
    private String requestDescription;
    private int numberOfResponses;




    public CardLayoutRequest(int id,String date, String requestDescription, String count ) {

        this.id=id;
        this.requestDate = date;
        this.requestDescription = requestDescription;
        this.numberOfResponses = Integer.parseInt(count);
    }

    public CardLayoutRequest(String date, String requestDescription, int numberOfResponses) {
        this.requestDate = date;
        this.requestDescription = requestDescription;
        this.numberOfResponses = numberOfResponses;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String  getDate() {
        return requestDate;
    }

    public void setDate(String date) {
        this.requestDate = date;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public int getNumberOfResponses() {
        return numberOfResponses;
    }

    public void setNumberOfResponses(int numberOfResponses) {
        this.numberOfResponses = numberOfResponses;
    }

}
