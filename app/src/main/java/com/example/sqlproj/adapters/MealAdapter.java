package com.example.sqlproj.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sqlproj.R;

public class MealAdapter extends BaseAdapter
{
    private Context context;
    private String[] firstMeal,
            mainMeal,
            sides,
            dessert,
            drink,
            price;
    public MealAdapter(Context context, String[] firstMeal, String[] mainMeal, String[] sides, String[] dessert, String[] drink, String[] price)
    {
        this.context = context;
        this.firstMeal = firstMeal;
        this.mainMeal = mainMeal;
        this.sides = sides;
        this.dessert = dessert;
        this.drink = drink;
        this.price = price;
    }

    @Override
    public int getCount()
    {
        return mainMeal.length;
    }

    @Override
    public Object getItem(int position)
    {
        return mainMeal[position];
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
        view = inflater.inflate(R.layout.meal_lv_layout, parent, false);
        TextView firstMealTextView = view.findViewById(R.id.first);
        TextView mainMealTextView = view.findViewById(R.id.mainMeal);
        TextView sidesTextView = view.findViewById(R.id.sides);
        TextView dessertTextView = view.findViewById(R.id.dessert);
        TextView drinkTextView = view.findViewById(R.id.drink);
        TextView priceTextView = view.findViewById(R.id.price);

        firstMealTextView.setText("First meal: " + firstMeal[position]);
        mainMealTextView.setText("Main meal: " + mainMeal[position]);
        sidesTextView.setText("Sides: " + sides[position]);
        dessertTextView.setText("Dessert: " + dessert[position]);
        drinkTextView.setText("Drink: " + drink[position]);
        priceTextView.setText("Price: " + price[position]);

        return view;
    }
}
