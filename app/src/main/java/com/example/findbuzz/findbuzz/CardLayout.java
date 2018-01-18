package com.example.findbuzz.findbuzz;

/**
 * Created by akash on 12/1/18.
 */

class CardLayout {

    private int id;
    private String username,user_email,userdescription;

    public CardLayout(int id, String username, String userdescription) {
        this.id = id;
        this.username = username;
        this.userdescription = userdescription;
    }
    public CardLayout(int id, String username, String email, String userdescription) {
        this.id = id;
        this.username = username;
        this.user_email = email;
        this.userdescription = userdescription;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
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
