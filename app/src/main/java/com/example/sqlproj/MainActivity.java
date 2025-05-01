package com.example.sqlproj;
import com.example.sqlproj.databaseClasses.Company;
import com.example.sqlproj.databaseClasses.Meal;
import com.example.sqlproj.databaseClasses.Order;
import com.example.sqlproj.databaseClasses.Worker;


import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}