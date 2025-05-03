package com.example.sqlproj.activitys;

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
import com.example.sqlproj.adapters.WorkerAdapter;
import com.example.sqlproj.databaseClasses.WorkerStats;

public class WorkerActivity extends AppCompatActivity 
{
    ListView listView;
    WorkerStats[] workers;

    AlertDialog.Builder adb;
    LinearLayout myDialog;
    EditText nameEd,companyEd,phoneEd;

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);
    }
    
    public void updateListView()
    {
        HelperDB dbHelper = new HelperDB(this);
        workers = dbHelper.getWorkers();
        String[] name,workingCompany,phoneNumber,cardId;
        name = new String[workers.length];
        workingCompany = new String[workers.length];
        phoneNumber = new String[workers.length];
        cardId = new String[workers.length];
        for(int i = 0; i < workers.length; i++)
        {
            name[i] = workers[i].getName() + " " + workers[i].getFamilyName();
            workingCompany[i] = workers[i].getCompanyName();
            phoneNumber[i] = String.valueOf(workers[i].getPhoneNumber());
            cardId[i] = String.valueOf(workers[i].getCardId());

        }
        BaseAdapter adapter = new WorkerAdapter(this, name, workingCompany, phoneNumber, cardId);
        listView.setAdapter(adapter);
    }

    public void addWorker(View view)
    {
        myDialog = (LinearLayout) getLayoutInflater().inflate(R.layout.worker_adb_layout, null);
        nameEd = myDialog.findViewById(R.id.getNameEd);
        companyEd = myDialog.findViewById(R.id.getCompanyEd);
        phoneEd = myDialog.findViewById(R.id.getPhoneNumberEd);
        adb = new AlertDialog.Builder(this);
        adb.setView(myDialog);
        adb.setTitle("Add Worker");
        adb.setPositiveButton("Add", (dialog, which) -> {
            String name = nameEd.getText().toString();
            String company = companyEd.getText().toString();
            String phone = phoneEd.getText().toString();
            if (name.isEmpty() || company.isEmpty() || phone.isEmpty())
            {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
            else if (phone.length() != 10)
            {
                Toast.makeText(this, "Phone number must be 10 digits", Toast.LENGTH_SHORT).show();
            }
            else if (!phone.matches("\\d+"))
            {
                Toast.makeText(this, "Phone number must contain only digits", Toast.LENGTH_SHORT).show();
            }
            else if (company.length() > 20)
            {
                Toast.makeText(this, "Company name is too long", Toast.LENGTH_SHORT).show();
            }
            else if (name.length() > 20)
            {
                Toast.makeText(this, "Name is too long", Toast.LENGTH_SHORT).show();

            }
            else if(!name.contains(" "))
            {
                Toast.makeText(this, "please enter full name", Toast.LENGTH_SHORT).show();
            }

            else if(company.contains(" "))
            {
                Toast.makeText(this, "Company name must not contain spaces", Toast.LENGTH_SHORT).show();
            }

            else
            {
                int phoneNumber = Integer.parseInt(phone);
                HelperDB dbHelper = new HelperDB(this);
                dbHelper.addNewWorker(name.substring(0,name.indexOf(" ")),name.substring(name.indexOf(" ")), company, phoneNumber);
                updateListView();
            }
        });
        adb.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        adb.show();
    }


    public void removeWorker(View view)
    {
        Toast.makeText(this, "click on the company you intend to delete", Toast.LENGTH_LONG).show();
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            HelperDB dbHelper = new HelperDB(this);
            dbHelper.deleteMeal(workers[position].getId());
            updateListView();
        });
    }

    public void goBack(View view)
    {
        finish();
    }
}