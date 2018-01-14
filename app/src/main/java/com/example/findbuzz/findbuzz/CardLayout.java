package com.example.findbuzz.findbuzz;

/**
 * Created by akash on 12/1/18.
 */

class CardLayout {

    private int id;
    private String username,userdescription;

    public CardLayout(int id, String username, String userdescription) {
        this.id = id;
        this.username = username;
        this.userdescription = userdescription;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserdescription() {
        return userdescription;
    }

    public void setUserdescription(String userdescription) {
        this.userdescription = userdescription;
    }


}
