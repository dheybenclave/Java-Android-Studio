package com.example.dheoclaveria.messenger;

/**
 * Created by Dheo Claveria on 8/12/2017.
 */

public class Conversation_Data {
    String type, name, body, date;

    public Conversation_Data(String type, String name, String body, String date) {
        this.type = type;
        this.name = name;
        this.body = body;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
