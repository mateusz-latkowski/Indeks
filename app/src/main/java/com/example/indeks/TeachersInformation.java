package com.example.indeks;

import androidx.annotation.NonNull;

public class TeachersInformation {

    private String Name;
    private String Surname;
    private String Email;
    private String Room;
    private String AcademicTitle;

    public TeachersInformation() {
    }

    public TeachersInformation(String name, String surname, String email, String room, String academicTitle) {
        this.Name = name;
        this.Surname = surname;
        this.Email = email;
        this.Room = room;
        this.AcademicTitle = academicTitle;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
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

    public String getAcademicTitle() {
        return AcademicTitle;
    }

    public void setAcademicTitle(String academicTitle) {
        AcademicTitle = academicTitle;
    }
}
