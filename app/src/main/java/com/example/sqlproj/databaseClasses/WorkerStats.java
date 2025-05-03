package com.example.sqlproj.databaseClasses;

public class WorkerStats
{

    String name;
    String familyName;
    String companyName;
    int id,cardId,phoneNumber;

    public WorkerStats(int id,String name, String familyName, String companyName, int cardId, int phoneNumber) {
        this.id = id;
        this.name = name;
        this.familyName = familyName;
        this.companyName = companyName;
        this.cardId = cardId;
        this.phoneNumber = phoneNumber;
    }
    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



}
