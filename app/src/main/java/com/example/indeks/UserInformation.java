package com.example.indeks;

public class UserInformation {

    public String Name;
    public String Surname;
    public String Pesel;
    public String Street;
    public String City;
    public String PhoneNumber;

    public UserInformation(String name, String surname, String pesel, String street, String city, String phoneNumber) {
        this.Name = name;
        this.Surname = surname;
        this.Pesel = pesel;
        this.Street = street;
        this.City = city;
        this.PhoneNumber = phoneNumber;
    }
}
