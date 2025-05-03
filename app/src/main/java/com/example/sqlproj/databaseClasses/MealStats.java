package com.example.sqlproj.databaseClasses;

public class MealStats
{
    String firstMeal,mainMeal,sides,dessert,drink;
    int price,id;

    public MealStats(int id,String firstMeal, String mainMeal, String sides, String dessert, String drink, int price) {
        this.id = id;
        this.firstMeal = firstMeal;
        this.mainMeal = mainMeal;
        this.sides = sides;
        this.dessert = dessert;
        this.drink = drink;
        this.price = price;
    }
    public int getId() {return id;}

    public void setId(int id) {this.id = id;}
    public String getFirstMeal() {
        return firstMeal;
    }

    public void setFirstMeal(String firstMeal) {
        this.firstMeal = firstMeal;
    }

    public String getMainMeal() {
        return mainMeal;
    }

    public void setMainMeal(String mainMeal) {
        this.mainMeal = mainMeal;
    }

    public String getSides() {
        return sides;
    }

    public void setSides(String sides) {
        this.sides = sides;
    }

    public String getDessert() {
        return dessert;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
