package com.example.indeks;

public class UserInformation {

    public String Name;
    public String Surname;
    public String Pesel;
    public String Street;
    public String City;
    public String PostalAddress;
    public String BirthdayDate;
    public String Study;
    public String PhoneNumber;
    public String Email;
    public String ID;

    public UserInformation(String name, String surname, String pesel, String street, String city, String postalAddress, String birthdayDate, String study, String phoneNumber, String email, String id) {
        this.Name = name;
        this.Surname = surname;
        this.Pesel = pesel;
        this.Street = street;
        this.City = city;
        this.PostalAddress = postalAddress;
        this.BirthdayDate = birthdayDate;
        this.Study = study;
        this.PhoneNumber = phoneNumber;
        this.Email = email;
        this.ID = id;
    }
}