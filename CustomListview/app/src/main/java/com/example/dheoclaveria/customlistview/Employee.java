package com.example.dheoclaveria.customlistview;

/**
 * Created by Dheo Claveria on 7/15/2017.
 */

public class Employee {


    String id, fullname, position, address, mobile, email;

    public Employee(String id, String fullname, String position, String address, String mobile, String email) {

        this.id = id;
        this.fullname = fullname;
        this.position = position;
        this.address = address;
        this.mobile = mobile;
        this.email = email;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
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
