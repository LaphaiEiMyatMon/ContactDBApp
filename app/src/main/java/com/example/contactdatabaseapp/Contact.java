package com.example.contactdatabaseapp;

public class Contact {

    private int _id;
    String name, dob, email;

    private int imageID;
    public Contact(int _id, String name, String dob, String email, int imageID) {
        this._id = _id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.imageID = imageID;

    }

    public Contact(String name, String dob, String email,  int imageID) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.imageID = imageID;

    }

    public int get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public int getImageID() {
        return imageID;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
