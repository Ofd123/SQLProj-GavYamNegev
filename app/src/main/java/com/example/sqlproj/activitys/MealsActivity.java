package com.example.sqlproj.activitys;

import static java.lang.String.valueOf;

import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sqlproj.HelperDB;
import com.example.sqlproj.R;
import com.example.sqlproj.adapters.CompanyAdapter;
import com.example.sqlproj.adapters.MealAdapter;
import com.example.sqlproj.databaseClasses.MealStats;


public class MealsActivity extends AppCompatActivity
{
    ListView listView;
    MealStats[] meals;

    AlertDialog.Builder adb;
    LinearLayout myDialog;
    EditText firstMealEd,mainMealEd,sidesEd,dessertEd,drinkEd,priceEd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        listView = findViewById(R.id.showingMeals);
    }

    public void updateListView()
    {
        HelperDB dbHelper = new HelperDB(this);
        meals = dbHelper.getMeals();
        String[] firstMeal,mainMeal,sides,dessert,drink,price;
        firstMeal = new String[meals.length];
        mainMeal = new String[meals.length];
        sides = new String[meals.length];
        dessert = new String[meals.length];
        drink = new String[meals.length];
        price = new String[meals.length];
        for(int i = 0; i < meals.length; i++)
        {
            firstMeal[i] = meals[i].getFirstMeal();
            mainMeal[i] = meals[i].getMainMeal();
            sides[i] = meals[i].getSides();
            dessert[i] = meals[i].getDessert();
            drink[i] = meals[i].getDrink();
            price[i] = valueOf(meals[i].getPrice());
        }
        BaseAdapter adapter = new MealAdapter(this, firstMeal, mainMeal, sides, dessert, drink, price);
        listView.setAdapter(adapter);
    }

    public void newMeal(View view)
    {
        myDialog = (LinearLayout) getLayoutInflater().inflate(R.layout.meal_adb_layout, null);
        firstMealEd = myDialog.findViewById(R.id.firstMealEd);
        mainMealEd = myDialog.findViewById(R.id.mainMealEd);
        sidesEd = myDialog.findViewById(R.id.sidesEd);
        dessertEd = myDialog.findViewById(R.id.dessertEd);
        drinkEd = myDialog.findViewById(R.id.drinksEd);
        priceEd = myDialog.findViewById(R.id.priceEd);

        adb = new AlertDialog.Builder(this);
        adb.setTitle("Add new meal");
        adb.setView(myDialog);
        adb.setPositiveButton("Add", (dialog, which) -> {
            if (firstMealEd.getText().toString().isEmpty() || mainMealEd.getText().toString().isEmpty() || sidesEd.getText().toString().isEmpty() || dessertEd.getText().toString().isEmpty() || drinkEd.getText().toString().isEmpty() || priceEd.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            } else if (Double.parseDouble(priceEd.getText().toString()) < 0) {
                Toast.makeText(this, "Price cannot be negative", Toast.LENGTH_SHORT).show();
                return;
            } else if (firstMealEd.getText().toString().length() > 20 || mainMealEd.getText().toString().length() > 20 || sidesEd.getText().toString().length() > 20 || dessertEd.getText().toString().length() > 20 || drinkEd.getText().toString().length() > 20) {
                Toast.makeText(this, "Field length cannot exceed 20 characters", Toast.LENGTH_SHORT).show();
                return;
            } else {
                HelperDB dbHelper = new HelperDB(this);
                dbHelper.addNewMeal(firstMealEd.getText().toString(), mainMealEd.getText().toString(), sidesEd.getText().toString(), dessertEd.getText().toString(), drinkEd.getText().toString(), Integer.parseInt(priceEd.getText().toString()));
                updateListView();
            }
        });
        adb.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        adb.show();
    }


    public void removeMeal(View view)
    {
        Toast.makeText(this, "click on the company you intend to delete", Toast.LENGTH_LONG).show();
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            HelperDB dbHelper = new HelperDB(this);
            dbHelper.deleteMeal(meals[position].getId());
            updateListView();
        });
    }

    public void goBack(View view)
    {
        finish();
    }
}