package com.example.indeks;

import java.util.Date;

public class ChatMessage {

    private String Text;
    private String User;
    private long Time;

    public ChatMessage() {}

    public ChatMessage(String text, String user) {
        this.Text = text;
        this.User = user;

        this.Time = new Date().getTime();
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public long getTime() {
        return Time;
    }

    public void setTime(long time) {
        Time = time;
    }
}