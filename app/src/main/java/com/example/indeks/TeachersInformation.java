package com.example.indeks;

import androidx.annotation.NonNull;

public class TeachersInformation {
    private String Name;
    private String Email;
    private String Room;

    public TeachersInformation() {
    }

    public TeachersInformation(String name, String email, String room) {
        this.Name = name;
        this.Email = email;
        this.Room = room;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    @NonNull
    @Override
    public String toString() {
        return this.Name + " - " + this.Room;
    }
}
