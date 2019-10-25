package com.example.indeks;

public class UserInformation {

    public String Name;
    public String Surname;
    public String Pesel_ID;
    public String Street;
    public String City;
    public String PhoneNumber;

    public UserInformation(String name, String surname, String pesel_id, String street, String city, String phoneNumber) {
        this.Name = name;
        this.Surname = surname;
        this.Pesel_ID = pesel_id;
        this.Street = street;
        this.City = city;
        this.PhoneNumber = phoneNumber;
    }
}