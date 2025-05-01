package com.example.sqlproj;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqlproj.databaseClasses.Company;
import com.example.sqlproj.databaseClasses.Meal;
import com.example.sqlproj.databaseClasses.Order;
import com.example.sqlproj.databaseClasses.Worker;

import java.util.ArrayList;
import java.util.List;

public class HelperDB extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "SQLProj.db";
    public static final int DATABASE_VERSION = 1;
    String strCreate,strDelete;

    public HelperDB(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
//-----------------------------------------------------------------------------------------------
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        strCreate = "CREATE TABLE " + Company.TABLE_NAME + " (" +
                Company.COMPANY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Company.COMPANY_NAME + " TEXT NOT NULL, " +
                Company.MAIN_PHONE_NUMBER + " INTEGER NOT NULL, " +
                Company.SECONDARY_PHONE_NUMBER + " INTEGER);";
        db.execSQL(strCreate);
        //----------------------------------------------------------------
        strCreate = "CREATE TABLE " + Meal.TABLE_NAME + " (" +
                Meal.MEAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Meal.FIRST_MEAL + " TEXT, " +
                Meal.MAIN_MEAL + " TEXT NOT NULL," +
                Meal.SIDES + " TEXT, " +
                Meal.DESSERT + " TEXT, " +
                Meal.DRINK + " TEXT NOT NULL, " +
                Meal.PRICE + " INTEGER NOT NULL);";
        db.execSQL(strCreate);
        //----------------------------------------------------------------
        strCreate = "CREATE TABLE \"" + Order.TABLE_NAME + "\" (" +
                Order.ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Order.SUPPLYING_COMPANY + " INTEGER NOT NULL, " +
                Order.ORDERED_MEAL + " INTEGER NOT NULL, " +
                Order.ORDER_WORKER_ID + " INTEGER NOT NULL, " +
                Order.DATE + " TEXT NOT NULL, " +
                Order.TIME + " TEXT NOT NULL);";
        db.execSQL(strCreate);
        //----------------------------------------------------------------
        strCreate = "CREATE TABLE " + Worker.TABLE_NAME + " (" +
                Worker.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Worker.CARD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Worker.NAME + " TEXT NOT NULL, " +
                Worker.FAMILY_NAME + " TEXT NOT NULL, " +
                Worker.COMPANY_NAME + " TEXT NOT NULL, " +
                Worker.PHONE_NUMBER + " INTEGER NOT NULL);";
        db.execSQL(strCreate);
    }
    //-----------------------------------------------------------------------------------------------
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        strDelete = "DROP TABLE IF EXISTS " + Company.TABLE_NAME;
        db.execSQL(strDelete);
        strDelete = "DROP TABLE IF EXISTS " + Worker.TABLE_NAME;
        db.execSQL(strDelete);
        strDelete = "DROP TABLE IF EXISTS " + Meal.TABLE_NAME;
        db.execSQL(strDelete);
        strDelete = "DROP TABLE IF EXISTS \"" + Order.TABLE_NAME + "\"";
        db.execSQL(strDelete);
        onCreate(db);
    }
    //-----------------------------------------------------------------------------------------------
    public boolean doesWorkerExist(int cardID, String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Worker.TABLE_NAME + " WHERE (" +
                Worker.CARD_ID + "=" + String.valueOf(cardID) + " AND " +
                Worker.NAME + "='" + name + "')";
        return db.rawQuery(query, null).getCount() > 0;
    }
    //-----------------------------------------------------------------------------------------------
    public boolean doesMealExist(int mealID, String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Meal.TABLE_NAME + " WHERE (" +
                Meal.MEAL_ID + "=" + String.valueOf(mealID) + " AND " +
                Meal.MAIN_MEAL + "='" + name + "')";
        return db.rawQuery(query, null).getCount() > 0;
    }
    //-----------------------------------------------------------------------------------------------
    public boolean doesCompanyExist(int companyID, String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Company.TABLE_NAME + " WHERE (" +
                Company.COMPANY_ID + "=" + String.valueOf(companyID) + " AND " +
                Company.COMPANY_NAME + "='" + name + "')";
        return db.rawQuery(query, null).getCount() > 0;
    }
    //-----------------------------------------------------------------------------------------------
    public boolean doesOrderExist(int orderID, String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Order.TABLE_NAME + " WHERE (" +
                Order.ORDER_ID + "=" + String.valueOf(orderID) +"')";
        return db.rawQuery(query, null).getCount() > 0;
    }
    //-----------------------------------------------------------------------------------------------
    public boolean addNewWorker(String name, String familyName, String companyName, int phoneNumber)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "INSERT INTO " + Worker.TABLE_NAME + " (" +
                    Worker.NAME + ", " +
                    Worker.FAMILY_NAME + ", " +
                    Worker.COMPANY_NAME + ", " +
                    Worker.PHONE_NUMBER + ") VALUES (" +
                    name + "', '" +
                    familyName + "', '" +
                    companyName + "', " +
                    phoneNumber + ");";
            db.execSQL(query);
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error adding new worker: " + e.getMessage());
            return false;
        }
    }
    //-----------------------------------------------------------------------------------------------
    public boolean addNewMeal(String firstMeal, String mainMeal, String sides, String dessert, String drink, int price)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "INSERT INTO " + Meal.TABLE_NAME + " (" +
                    Meal.FIRST_MEAL + ", " +
                    Meal.MAIN_MEAL + ", " +
                    Meal.SIDES + ", " +
                    Meal.DESSERT + ", " +
                    Meal.DRINK + ", " +
                    Meal.PRICE + ") VALUES (" +
                    firstMeal + "', '" +
                    mainMeal + "', '" +
                    sides + "', '" +
                    dessert + "', '" +
                    drink + "', " +
                    price + ");";
            db.execSQL(query);
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error adding new meal: " + e.getMessage());
            return false;
        }
    }
    //-----------------------------------------------------------------------------------------------
    public boolean addNewCompany(String name, int mainPhoneNumber, int secondaryPhoneNumber)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "INSERT INTO " + Company.TABLE_NAME + " (" +
                    Company.COMPANY_NAME + ", " +
                    Company.MAIN_PHONE_NUMBER + ", " +
                    Company.SECONDARY_PHONE_NUMBER + ") VALUES (" +
                    name + "', '" +
                    mainPhoneNumber + "', '" +
                    secondaryPhoneNumber + ");";
            db.execSQL(query);
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error adding new company: " + e.getMessage());
            return false;
        }
    }
    //-----------------------------------------------------------------------------------------------
    public boolean addNewOrder(int supplyingCompany, int orderedMeal, int orderWorkerID, String date, String time)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "INSERT INTO " + Order.TABLE_NAME + " (" +
                    Order.SUPPLYING_COMPANY + ", " +
                    Order.ORDERED_MEAL + ", " +
                    Order.ORDER_WORKER_ID + ", " +
                    Order.DATE + ", " +
                    Order.TIME + ") VALUES (" +
                    supplyingCompany + ", '" +
                    orderedMeal + "', '" +
                    orderWorkerID + "', '" +
                    date + "', '" +
                    time + ");";
            db.execSQL(query);
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error adding new order: " + e.getMessage());
            return false;
        }
    }
    //-----------------------------------------------------------------------------------------------
    public int[] getWorkersActiveOrders(int workerID)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Order.TABLE_NAME + " WHERE (" +
                Order.ORDER_WORKER_ID + "=" + workerID + ")";

        Cursor cursor = db.rawQuery(query, null);
        List<Integer> ordersList = new ArrayList<>();
        while (cursor.moveToNext())
        {
            int columnIndex = cursor.getColumnIndex(Order.ORDER_ID);
            if (columnIndex != -1)
            {
                ordersList.add(cursor.getInt(columnIndex));
            }
        }
        cursor.close();
        // Convert the list to an array
        int[] orders = new int[ordersList.size()];
        for (int i = 0; i < ordersList.size(); i++)
        {
            orders[i] = ordersList.get(i);
        }
        return orders;
    }
    //-----------------------------------------------------------------------------------------------

    public boolean deleteWorker(int workerID)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "DELETE FROM " + Worker.TABLE_NAME + " WHERE " +
                    Worker.ID + "=" + String.valueOf(workerID) + ";";
            db.execSQL(query);
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error deleting worker: " + e.getMessage());
            return false;
        }
    }
    //-----------------------------------------------------------------------------------------------
    public boolean deleteMeal(int mealID)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "DELETE FROM " + Meal.TABLE_NAME + " WHERE " +
                    Meal.MEAL_ID + "=" + String.valueOf(mealID) + ";";
            db.execSQL(query);
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error deleting meal: " + e.getMessage());
            return false;
        }
    }
    //-----------------------------------------------------------------------------------------------
    public boolean deleteCompany(int companyID)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "DELETE FROM " + Company.TABLE_NAME + " WHERE " +
                    Company.COMPANY_ID + "=" + String.valueOf(companyID) + ";";
            db.execSQL(query);
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error deleting company: " + e.getMessage());
            return false;
        }
    }
    //-----------------------------------------------------------------------------------------------
    public boolean deleteOrder(int orderID)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "DELETE FROM " + Order.TABLE_NAME + " WHERE " +
                    Order.ORDER_ID + "=" + String.valueOf(orderID) + ";";
            db.execSQL(query);
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error deleting order: " + e.getMessage());
            return false;
        }
    }
}
