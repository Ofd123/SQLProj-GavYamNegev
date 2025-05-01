package com.example.sqlproj.databaseClasses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Worker
{
    public static final String TABLE_NAME = "WORKER";
    public static final String ID = "_id";
    public static final String CARD_ID = "CARD_ID";
    public static final String NAME = "NAME";
    public static final String FAMILY_NAME = "FAMILY_NAME";
    public static final String COMPANY_NAME = "COMPANY_NAME";
    public static final String PHONE_NUMBER = "PHONE_NUMBER";

    public static class DB_helper extends SQLiteOpenHelper
    {
        public static final String DATABASE_NAME = "GYNbusDB.db";
        public static final int DATABASE_VERSION = 1;
        String strCreate,strDelete;





        public DB_helper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
    //--------------------------------------------------------------------------------------------------
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            strCreate = "CREATE TABLE " +

        }
    //--------------------------------------------------------------------------------------------------
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {

        }
    }
}
