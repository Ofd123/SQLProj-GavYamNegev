package com.example.sqlproj.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sqlproj.R;

public class CompanyAdapter extends BaseAdapter
{
    private Context context;
    private String names[];
    private String mainPhoneNumber[];
    private String secPhoneNumber[];

    public CompanyAdapter(Context context, String names[], String mainPhoneNumber[], String secPhoneNumber[])
    {
        this.context = context;
        this.names = names;
        this.mainPhoneNumber = mainPhoneNumber;
        this.secPhoneNumber = secPhoneNumber;
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
        view = inflater.inflate(R.layout.company_lv_layout, parent, false);
        TextView nameTextView = view.findViewById(R.id.company_name);
        TextView mainPhoneTextView = view.findViewById(R.id.main_phone);
        TextView secPhoneTextView = view.findViewById(R.id.sec_phone);
        nameTextView.setText("Company name:" + names[position]);
        mainPhoneTextView.setText("main phone: " + mainPhoneNumber[position]);
        secPhoneTextView.setText("sec phone: " + secPhoneNumber[position]);
        return view;
    }
}