package com.example.sqlproj.databaseClasses;

public class OrderStats
{
    String date,time;
    int orderWorkerId,orderedMeal,supplyingCompany,id;

    public OrderStats(int id,String date, String time, int orderWorkerId, int orderedMeal, int supplyingCompany) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.orderWorkerId = orderWorkerId;
        this.orderedMeal = orderedMeal;
        this.supplyingCompany = supplyingCompany;
    }
    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getOrderWorkerId() {
        return orderWorkerId;
    }

    public void setOrderWorkerId(int orderWorkerId) {
        this.orderWorkerId = orderWorkerId;
    }

    public int getOrderedMeal()
    {
        return orderedMeal;
    }

    public void setOrderedMeal(int orderedMeal) {
        this.orderedMeal = orderedMeal;
    }

    public int getSupplyingCompany() {
        return supplyingCompany;
    }

    public void setSupplyingCompany(int supplyingCompany) {
        this.supplyingCompany = supplyingCompany;
    }
}
