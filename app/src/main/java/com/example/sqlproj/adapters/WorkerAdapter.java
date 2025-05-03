package com.example.sqlproj.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sqlproj.R;

public class WorkerAdapter extends BaseAdapter
{
    private Context context;
    private String names[];
    private String phoneNumber[];
    private String workingCompany[];
    private String cardID[];
    public WorkerAdapter(Context context, String names[], String[] company, String phoneNumber[], String cardID[])
    {
        this.context = context;
        this.names = names;
        this.workingCompany = company;
        this.phoneNumber = phoneNumber;
        this.cardID = cardID;
    }

    @Override
    public int getCount()
    {
        return names.length;
    }

    @Override
    public Object getItem(int position)
    {
        return names[position];
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.worker_lv_layout, parent, false);
        TextView nameTextView = view.findViewById(R.id.fullName);
        TextView mainPhoneTextView = view.findViewById(R.id.phoneNumber);
        TextView cardId = view.findViewById(R.id.cardId);
        TextView workingCompanyTextView = view.findViewById(R.id.workingCompany);
        nameTextView.setText("worker name:" + names[position]);
        cardId.setText("card id: " + cardID[position]);
        workingCompanyTextView.setText("Company name:" + workingCompany[position]);
        mainPhoneTextView.setText("phone number: " + phoneNumber[position]);
        return view;
    }
}
