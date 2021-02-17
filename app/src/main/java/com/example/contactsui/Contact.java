package com.example.contactsui;

public class Contact {
    private int id;
    private String name;
    private String phoneNumber;
    private byte[] image;

    public Contact() {
    }

    public Contact(int id, String name, String phoneNumber, byte[] image) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.image = image;
    }

    public Contact(String name, String phoneNumber, byte[] image) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.image = image;
    }

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    public Contact(int id,String name, String phoneNumber) {
        this.id = id ;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}


