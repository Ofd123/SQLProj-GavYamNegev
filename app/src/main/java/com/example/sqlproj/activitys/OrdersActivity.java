package com.example.sqlproj.activitys;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import static java.lang.String.valueOf;

import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlproj.HelperDB;
import com.example.sqlproj.R;
import com.example.sqlproj.adapters.OrderAdapter;
import com.example.sqlproj.databaseClasses.OrderStats;

public class OrdersActivity extends AppCompatActivity
{
    ListView listView;
    OrderStats[] orders;

    AlertDialog.Builder adb;
    LinearLayout myDialog;
    EditText nameEd,companyEd,mealEd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        listView = findViewById(R.id.showOrders);
    }

    public void updateListView()
    {
        HelperDB dbHelper = new HelperDB(this);
        orders = dbHelper.getOrders();
        String[] orderIDs = new String[orders.length];
        String[] mainMeals = new String[orders.length];
        String[] workerNames = new String[orders.length];
        String[] workingCompanies = new String[orders.length];
        String[] fullDates = new String[orders.length];
        for(int i = 0; i < orders.length; i++)
        {
            orderIDs[i] = valueOf(orders[i].getId());
            mainMeals[i] = valueOf(orders[i].getOrderedMeal());
            workerNames[i] = dbHelper.getWorkerNameById(orders[i].getOrderWorkerId());
            workingCompanies[i] = dbHelper.getCompanyNameById(orders[i].getSupplyingCompany());
            fullDates[i] = orders[i].getDate() + " " + orders[i].getTime();
        }
        BaseAdapter adapter = new OrderAdapter(this, orderIDs, mainMeals, workerNames, workingCompanies, fullDates);
        listView.setAdapter(adapter);
    }

    public void newOrder(View view)
    {
        HelperDB dbHelper = new HelperDB(this);
        myDialog = (LinearLayout) getLayoutInflater().inflate(R.layout.order_adb_layout, null);
        nameEd = myDialog.findViewById(R.id.workerIdEd);
        companyEd = myDialog.findViewById(R.id.companyNameEd);
        mealEd = myDialog.findViewById(R.id.mealNameEd);
        adb = new AlertDialog.Builder(this);
        adb.setView(myDialog);
        adb.setTitle("Add Order");
        adb.setPositiveButton("Add", (dialog, which) -> {
            if(nameEd.getText().toString().isEmpty() || companyEd.getText().toString().isEmpty() || mealEd.getText().toString().isEmpty())
            {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_LONG).show();
                return;
            }
            if(!dbHelper.doesCompanyExist(companyEd.getText().toString()))
            {
                Toast.makeText(this, "Company does not exist", Toast.LENGTH_LONG).show();
                return;
            }
            if(!dbHelper.doesWorkerExist(Integer.parseInt(nameEd.getText().toString())))
            {
                Toast.makeText(this, "Worker does not exist", Toast.LENGTH_LONG).show();
                return;
            }
            if(!dbHelper.doesMealExist(mealEd.getText().toString()))
            {
                Toast.makeText(this, "Meal does not exist", Toast.LENGTH_LONG).show();
                return;
            }
            else
            {
                int cardId = Integer.parseInt(nameEd.getText().toString());
                int companyId = Integer.parseInt(companyEd.getText().toString());
                int mealId = Integer.parseInt(mealEd.getText().toString());

                String date = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(new java.util.Date());
                String time = new java.text.SimpleDateFormat("HH:mm:ss", java.util.Locale.getDefault()).format(new java.util.Date());

                dbHelper.addNewOrder(companyId, cardId, mealId, date, time);
                updateListView();
            }
        });
        adb.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.dismiss();
        });
        adb.show();
    }


    public void removeOrder(View view)
    {
        Toast.makeText(this, "click on the company you intend to delete", Toast.LENGTH_LONG).show();
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            HelperDB dbHelper = new HelperDB(this);
            dbHelper.deleteOrder(orders[position].getId());
            updateListView();
        });
    }

    public void goBack(View view)
    {
        finish();
    }
}