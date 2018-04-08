package com.example.ruiz.customlistview;

/**
 * Created by Ruiz on 7/15/2017.
 */

public class Employee {

    private String ID , fname , position , address , mobile , email ;
    public Employee(String id , String fullname , String pos , String addr , String mob , String email1){

        this.ID = id;
        this.fname = fullname;
        this.position = pos;
        this.address = addr;
        this.mobile = mob;
        this.email = email1;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFname() {
        return fname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}

