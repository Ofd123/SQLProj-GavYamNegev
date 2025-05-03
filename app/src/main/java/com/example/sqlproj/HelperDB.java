package com.example.sqlproj;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqlproj.databaseClasses.Company;
import com.example.sqlproj.databaseClasses.CompanyStats;
import com.example.sqlproj.databaseClasses.Meal;
import com.example.sqlproj.databaseClasses.MealStats;
import com.example.sqlproj.databaseClasses.Order;
import com.example.sqlproj.databaseClasses.OrderStats;
import com.example.sqlproj.databaseClasses.Worker;
import com.example.sqlproj.databaseClasses.WorkerStats;

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
                Worker.CARD_ID + " INTEGER UNIQUE, " +
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
    public boolean doesWorkerExist(int cardID)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Worker.TABLE_NAME + " WHERE (" +
                Worker.CARD_ID + "=" + String.valueOf(cardID) + "')";
        return db.rawQuery(query, null).getCount() > 0;
    }
    //-----------------------------------------------------------------------------------------------
    public boolean doesMealExist(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Meal.TABLE_NAME + " WHERE (" +
                Meal.MAIN_MEAL + "='" + name + "')";
        return db.rawQuery(query, null).getCount() > 0;
    }
    //-----------------------------------------------------------------------------------------------
    public boolean doesCompanyExist(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Company.TABLE_NAME + " WHERE (" +
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
        //--------------------------------------------
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
        //--------------------------------------------
        int[] orders = new int[ordersList.size()];
        for (int i = 0; i < ordersList.size(); i++)
        {
            orders[i] = ordersList.get(i);
        }
        return orders;
    }
    //-----------------------------------------------------------------------------------------------
    public int[] getCompanysWorkers(int companyID)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Worker.TABLE_NAME + " WHERE (" +
                Worker.COMPANY_NAME + "=" + companyID + ")";
        //--------------------------------------------
        Cursor cursor = db.rawQuery(query, null);
        List<Integer> workersList = new ArrayList<>();
        while (cursor.moveToNext())
        {
            int columnIndex = cursor.getColumnIndex(Worker.ID);
            if (columnIndex != -1)
            {
                workersList.add(cursor.getInt(columnIndex));
            }
        }
        cursor.close();
        //--------------------------------------------
        int[] workers = new int[workersList.size()];
        for (int i = 0; i < workersList.size(); i++)
        {
            workers[i] = workersList.get(i);
        }
        return workers;
    }
    //-----------------------------------------------------------------------------------------------
    public boolean deleteWorker(int workerID)
    {
        //delete the worker's active orders
        int[] orders = getWorkersActiveOrders(workerID);
        for (int i = 0; i< orders.length; i++)
        {
            deleteOrder(orders[i]);
        }
        //-------------------------------------------
        try
        {

            SQLiteDatabase db = this.getWritableDatabase();
            String query = "DELETE FROM " + Worker.TABLE_NAME + " WHERE " +
                    Worker.ID + "=" + String.valueOf(workerID) + ";";
            db.execSQL(query);

        }
        catch(Exception e)
        {
            System.out.println("Error deleting worker: " + e.getMessage());
            return false;
        }
        return true;
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
        //delete the company's workers
        int[] workers = getCompanysWorkers(companyID);
        for (int workerID : workers)
        {
            deleteWorker(workerID);
        }
        //--------------------------------------------
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
    //-----------------------------------------------------------------------------------------------
    public WorkerStats[] getWorkers()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Worker.TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        List<WorkerStats> workersList = new ArrayList<>();

        try {
            while (cursor.moveToNext())
            {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(Worker.ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(Worker.NAME));
                String familyName = cursor.getString(cursor.getColumnIndexOrThrow(Worker.FAMILY_NAME));
                String companyName = cursor.getString(cursor.getColumnIndexOrThrow(Worker.COMPANY_NAME));
                int cardId = cursor.getInt(cursor.getColumnIndexOrThrow(Worker.CARD_ID));
                int phoneNumber = cursor.getInt(cursor.getColumnIndexOrThrow(Worker.PHONE_NUMBER));

                workersList.add(new WorkerStats(id,name, familyName, companyName, cardId, phoneNumber));
            }
        }
        catch (Exception e)
        {
            System.out.println("Error reading worker stats: " + e.getMessage());
        }
        finally
        {
            cursor.close();
        }
        return workersList.toArray(new WorkerStats[0]);
    }
    //-----------------------------------------------------------------------------------------------
    public CompanyStats[] getCompanies()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Company.TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        List<CompanyStats> companiesList = new ArrayList<>();

        try {
            while (cursor.moveToNext())
            {
                int companyID = cursor.getInt(cursor.getColumnIndexOrThrow(Company.COMPANY_ID));
                String companyName = cursor.getString(cursor.getColumnIndexOrThrow(Company.COMPANY_NAME));
                int mainPhoneNumber = cursor.getInt(cursor.getColumnIndexOrThrow(Company.MAIN_PHONE_NUMBER));
                int secondaryPhoneNumber = cursor.getInt(cursor.getColumnIndexOrThrow(Company.SECONDARY_PHONE_NUMBER));

                companiesList.add(new CompanyStats(companyID, companyName, mainPhoneNumber, secondaryPhoneNumber));
            }
        }
        catch (Exception e)
        {
            System.out.println("Error reading companies: " + e.getMessage());
        }
        finally
        {
            cursor.close();
        }
        return companiesList.toArray(new CompanyStats[0]);
    }
    //-----------------------------------------------------------------------------------------------
    public OrderStats[] getOrders()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM \"" + Order.TABLE_NAME + "\"";

        Cursor cursor = db.rawQuery(query, null);
        List<OrderStats> ordersList = new ArrayList<>();

        try {
            while (cursor.moveToNext())
            {
                int orderID = cursor.getInt(cursor.getColumnIndexOrThrow(Order.ORDER_ID));
                int supplyingCompany = cursor.getInt(cursor.getColumnIndexOrThrow(Order.SUPPLYING_COMPANY));
                int orderedMeal = cursor.getInt(cursor.getColumnIndexOrThrow(Order.ORDERED_MEAL));
                int orderWorkerID = cursor.getInt(cursor.getColumnIndexOrThrow(Order.ORDER_WORKER_ID));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(Order.DATE));
                String time = cursor.getString(cursor.getColumnIndexOrThrow(Order.TIME));

                ordersList.add(new OrderStats(orderID, date, time, orderWorkerID, orderedMeal, supplyingCompany));
            }
        }
        catch (Exception e)
        {
            System.out.println("Error reading orders: " + e.getMessage());
        }
        finally
        {
            cursor.close();
        }
        return ordersList.toArray(new OrderStats[0]);
    }
    public MealStats[] getMeals()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Meal.TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        List<MealStats> mealsList = new ArrayList<>();

        try {
            while (cursor.moveToNext())
            {
                int mealID = cursor.getInt(cursor.getColumnIndexOrThrow(Meal.MEAL_ID));
                String firstMeal = cursor.getString(cursor.getColumnIndexOrThrow(Meal.FIRST_MEAL));
                String mainMeal = cursor.getString(cursor.getColumnIndexOrThrow(Meal.MAIN_MEAL));
                String sides = cursor.getString(cursor.getColumnIndexOrThrow(Meal.SIDES));
                String dessert = cursor.getString(cursor.getColumnIndexOrThrow(Meal.DESSERT));
                String drink = cursor.getString(cursor.getColumnIndexOrThrow(Meal.DRINK));
                int price = cursor.getInt(cursor.getColumnIndexOrThrow(Meal.PRICE));

                mealsList.add(new MealStats(mealID, firstMeal, mainMeal, sides, dessert, drink, price));
            }
        }
        catch (Exception e)
        {
            System.out.println("Error reading meals: " + e.getMessage());
        }
        finally
        {
            cursor.close();
        }
        return mealsList.toArray(new MealStats[0]);
    }
    public String getWorkerNameById(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Worker.TABLE_NAME + " WHERE (" +
                Worker.ID + "=" + id + ")";
        Cursor cursor = db.rawQuery(query, null);
        String name = "";
        if (cursor.moveToFirst())
        {
            name = cursor.getString(cursor.getColumnIndexOrThrow(Worker.NAME));
        }
        cursor.close();
        return name;
    }
    public String getCompanyNameById(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Company.TABLE_NAME + " WHERE (" +
                Company.COMPANY_ID + "=" + id + ")";
        Cursor cursor = db.rawQuery(query, null);
        String name = "";
        if (cursor.moveToFirst())
        {
            name = cursor.getString(cursor.getColumnIndexOrThrow(Company.COMPANY_NAME));
        }
        cursor.close();
        return name;
    }
    public String getMealNameById(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Meal.TABLE_NAME + " WHERE (" +
                Meal.MEAL_ID + "=" + id + ")";
        Cursor cursor = db.rawQuery(query, null);
        String name = "";
        if (cursor.moveToFirst())
        {
            name = cursor.getString(cursor.getColumnIndexOrThrow(Meal.MAIN_MEAL));
        }
        cursor.close();
        return name;
    }

}
