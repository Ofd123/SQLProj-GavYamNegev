package com.example.sqlproj.activitys;
import com.example.sqlproj.R;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void viewCompanies(View view)
    {
        Intent intent = new Intent(this, CompanyActivity.class);
        startActivity(intent);
    }
    public void viewEmployees(View view)
    {
        Intent intent = new Intent(this, WorkerActivity.class);
        startActivity(intent);
    }
    public void viewMeals(View view)
    {
        Intent intent = new Intent(this, MealsActivity.class);
        startActivity(intent);
    }
    public void viewOrders(View view)
    {
        Intent intent = new Intent(this, OrdersActivity.class);
        startActivity(intent);
    }
    public void viewCredits(View view)
    {
        Intent intent = new Intent(this, Credits.class);
        startActivity(intent);
    }
}