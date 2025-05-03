package com.example.sqlproj.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sqlproj.R;

public class OrderAdapter extends BaseAdapter
{
    private Context context;
    private String orderID[];
    private String meal[];
    private String workerName[];
    private String workingCompany[];
    private String fullDate[];
    public OrderAdapter(Context context, String id[], String[] meal, String workerName[], String company[], String fullDate[])
    {
        this.context = context;
        this.orderID = id;
        this.meal = meal;
        this.workerName = workerName;
        this.workingCompany = company;
        this.fullDate = fullDate;
    }

    @Override
    public int getCount()
    {
        return orderID.length;
    }

    @Override
    public Object getItem(int position)
    {
        return orderID[position];
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
        view = inflater.inflate(R.layout.order_lv_layout, parent, false);
        TextView orderId = view.findViewById(R.id.orderId);
        TextView orderedMeal = view.findViewById(R.id.orderedMeal);
        TextView workerName = view.findViewById(R.id.workingWorker);
        TextView workingCompany = view.findViewById(R.id.workingCompany);
        TextView fullDate = view.findViewById(R.id.fullDate);

        orderId.setText("order id:" + orderID[position]);
        orderedMeal.setText("ordered meal: " + meal[position]);
        workerName.setText("worker name:" + this.workerName[position]);
        workingCompany.setText("company name: " + this.workingCompany[position]);
        fullDate.setText("order date: " + this.fullDate[position]);
        return view;
    }
}
