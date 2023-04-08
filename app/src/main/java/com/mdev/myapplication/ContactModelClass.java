package com.mdev.myapplication;

public class ContactModelClass {

    Integer id;
    String name;
    String contact;

    public ContactModelClass(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public ContactModelClass(Integer id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

}
