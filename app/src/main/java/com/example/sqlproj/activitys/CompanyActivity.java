package com.example.sqlproj.activitys;

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

import com.example.sqlproj.R;
import com.example.sqlproj.adapters.CompanyAdapter;
import com.example.sqlproj.HelperDB;
import com.example.sqlproj.databaseClasses.CompanyStats;

public class CompanyActivity extends AppCompatActivity
{

    ListView listView;
    CompanyStats[] companies;

    AlertDialog.Builder adb;
    LinearLayout myDialog;
    EditText nameEd,mainPhoneEd,secPhoneEd;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        listView = findViewById(R.id.showList);
    }

    public void updateListView()
    {
        HelperDB dbHelper = new HelperDB(this);
        companies = dbHelper.getCompanies();
        String[] name,mainPhone,secPhone;
        name = new String[companies.length];
        mainPhone = new String[companies.length];
        secPhone = new String[companies.length];
        for(int i = 0; i < companies.length; i++)
        {
            name[i] = companies[i].getName();
            mainPhone[i] = valueOf(companies[i].getPhoneNumber());
            secPhone[i] = valueOf(companies[i].getSecondaryPhoneNumber());
        }
        BaseAdapter adapter = new CompanyAdapter(this, name, mainPhone, secPhone);
        listView.setAdapter(adapter);
    }

    public void newCompany(View view)
    {
        HelperDB dbHelper = new HelperDB(this);
        myDialog = (LinearLayout) getLayoutInflater().inflate(R.layout.company_adb_layout, null);
        nameEd = myDialog.findViewById(R.id.companyNameEd);
        mainPhoneEd = myDialog.findViewById(R.id.mainPhoneEd);
        secPhoneEd = myDialog.findViewById(R.id.secphonenumberEd);
        adb = new AlertDialog.Builder(this);
        adb.setView(myDialog);
        adb.setTitle("Add Company");
        adb.setPositiveButton("Add", (dialog, which) ->
        {
            if(nameEd.getText().toString().isEmpty() || mainPhoneEd.getText().toString().isEmpty() || secPhoneEd.getText().toString().isEmpty())
            {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_LONG).show();
                return;
            }
            else if(!mainPhoneEd.getText().toString().matches("[0-9]+") || !secPhoneEd.getText().toString().matches("[0-9]+"))
            {
                Toast.makeText(this, "Please enter valid phone numbers", Toast.LENGTH_LONG).show();
                return;
            }
            else if(mainPhoneEd.getText().toString().length() != 8 || secPhoneEd.getText().toString().length() != 8)
            {
                Toast.makeText(this, "Please enter valid phone numbers", Toast.LENGTH_LONG).show();
                return;
            }
            else if (dbHelper.doesCompanyExist(nameEd.getText().toString()))
            {
                Toast.makeText(this, "Company already exists", Toast.LENGTH_LONG).show();
                return;
            }
            else
            {
                String name = nameEd.getText().toString();
                int mainPhone = Integer.parseInt(mainPhoneEd.getText().toString());
                int secPhone = Integer.parseInt(secPhoneEd.getText().toString());

                dbHelper.addNewCompany(name, mainPhone, secPhone);
                updateListView();
            }
        });
        adb.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        adb.show();
    }


    public void deleteCompany(View view)
    {
        Toast.makeText(this, "click on the company you intend to delete", Toast.LENGTH_LONG).show();
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            HelperDB dbHelper = new HelperDB(this);
            dbHelper.deleteCompany(companies[position].getId());
            updateListView();
        });
    }

    public void goBack(View view)
    {
        finish();
    }
}