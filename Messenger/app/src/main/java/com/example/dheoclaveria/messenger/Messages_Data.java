package com.example.dheoclaveria.messenger;

/**
 * Created by Dheo Claveria on 8/8/2017.
 */

public class Messages_Data {

    String messages_id, messages_threadid, messages_body, messages_name, messages_photo, message_number;

    public Messages_Data(String messages_id, String messages_threadid, String messages_name, String message_number, String messages_body, String messages_photo) {

        this.messages_id = messages_id;
        this.messages_threadid = messages_threadid;
        this.messages_body = messages_body;
        this.messages_name = messages_name;
        this.message_number = message_number;
        this.messages_photo = messages_photo;
    }

    public String getMessages_id() {
        return messages_id;
    }

    public void setMessages_id(String messages_id) {
        this.messages_id = messages_id;
    }

    public String getMessages_threadid() {
        return messages_threadid;
    }

    public void setMessages_threadid(String messages_threadid) {
        this.messages_threadid = messages_threadid;
    }

    public String getMessages_name() {
        return messages_name;
    }

    public void setMessages_name(String messages_name) {
        this.messages_name = messages_name;
    }

    public String getMessages_body() {
        return messages_body;
    }

    public void setMessages_body(String messages_body) {
        this.messages_body = messages_body;
    }


    public String getMessages_photo() {
        return messages_photo;
    }

    public void setMessages_photo(String messages_photo) {
        this.messages_photo = messages_photo;
    }

    public String getMessage_number() {
        return message_number;
    }

    public void setMessage_number(String message_number) {
        this.message_number = message_number;
    }
}


