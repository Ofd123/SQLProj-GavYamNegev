package com.example.sqlproj.databaseClasses;

public class CompanyStats
{
    String name;
    int phoneNumber, secondaryPhoneNumber;
    int id;

    public CompanyStats(int id,String name, int phoneNumber, int secondaryPhoneNumber)
    {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.secondaryPhoneNumber = secondaryPhoneNumber;
    }
    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSecondaryPhoneNumber() {
        return secondaryPhoneNumber;
    }

    public void setSecondaryPhoneNumber(int secondaryPhoneNumber) {
        this.secondaryPhoneNumber = secondaryPhoneNumber;
    }
}
